package com.test.COCONSULT.JWT;

import com.test.COCONSULT.ServiceIMP.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthTokenFilter implements Filter {

    @Value("${cryptoserver.app.jwtSecret}")
    private String jwtSecret;

    @Value("${cryptoserver.app.jwtExpiration}")
    private int jwtExpiration;
    private JwtProvider tokenProvider;

    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);


    public void setAuthentication(String jwt, HttpServletRequest request) {
        String username = tokenProvider.getUserNameFromJwtToken(jwt);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


   /* @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String jwt = getJwt(request);
            if (jwt!=null && tokenProvider.validateJwtToken(jwt)) {
                String username = tokenProvider.getUserNameFromJwtToken(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("access")) {
            return authHeader.replace("access","");
        }

        return null;
    }*/
    
    // Modify JwtAuthTokenFilter to handle refresh tokens from session
    public String getJwt(HttpServletRequest request, HttpSession session) {
    // Check for access token
    String accessToken = extractAccessToken(request);
    if (accessToken != null) {
        return accessToken;
    }

    // Check for refresh token from session
    String refreshToken = (String) session.getAttribute("refreshToken");
    if (refreshToken != null && isValidRefreshToken(refreshToken)) {
        // Issue new access token
        String newAccessToken = issueNewAccessToken(refreshToken);

        // Return new access token
        return newAccessToken;
    }

    return null;
}

    public String issueNewAccessToken(String refreshToken) {
        try {
            // Parse the refresh token to get user details
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(refreshToken).getBody();
            String username = claims.getSubject();

            // Generate a new access token for the user
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
        } catch (Exception e) {
            logger.error("Error issuing new access token: {}", e.getMessage());
            return null;
        }
    }

    public boolean isValidRefreshToken(String refreshToken) {
        try {
            // Validate the refresh token's signature and expiration
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(refreshToken);
            return true;
        } catch (Exception e) {
            logger.error("Invalid refresh token: {}", e.getMessage());
            return false;
        }
    }

    public String extractAccessToken(HttpServletRequest request) {
        // Extract the access token from the Authorization header
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("access ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        try {
            String jwt = getJwt(httpRequest, session);
            if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
                // If the access token is valid, set the authentication
                setAuthentication(jwt, httpRequest);
            } else {
                // If the access token is expired or invalid, try refreshing it using the refresh token
                String refreshToken = (String) session.getAttribute("refreshToken");
                if (refreshToken != null && this.isValidRefreshToken(refreshToken)) {
                    // Issue a new access token using the refresh token
                    String newAccessToken = this.issueNewAccessToken(refreshToken);
                    if (newAccessToken != null) {
                        // If a new access token is issued successfully, set the authentication
                        setAuthentication(newAccessToken, httpRequest);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Can NOT set user authentication -> Message: {}", e);
        }

        chain.doFilter(request, response);
    }

}

