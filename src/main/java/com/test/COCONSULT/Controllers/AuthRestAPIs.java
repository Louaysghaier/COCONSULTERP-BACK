package com.test.COCONSULT.Controllers;


import com.test.COCONSULT.DTO.SignIn;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.JWT.JwtAuthTokenFilter;
import com.test.COCONSULT.JWT.JwtProvider;
import com.test.COCONSULT.JWT.JwtResponse;
import com.test.COCONSULT.Reposotories.RoleRepository;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.UserServiceIMP;
import com.test.COCONSULT.Services.MailSenderService;
import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserServiceIMP userServiceIMP;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    MailSenderService mailSending;
    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;
    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SignIn login, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        List <String> jwt = jwtProvider.generateJwtTokens(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt.get(0),jwt.get(1), userDetails.getUsername(), userDetails.getAuthorities()));
    }


    /* @PostMapping("/signIn")

    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody SignIn login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication)
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

    }*/
    // Create a new controller method for token refresh

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken( HttpServletRequest request) {
        String refreshToken = extractRefreshToken(request);
        if (refreshToken != null && jwtAuthTokenFilter.isValidRefreshToken(refreshToken)) {
            String newAccessToken = jwtAuthTokenFilter.issueNewAccessToken(refreshToken);
            String newRefreshToken = jwtProvider.generateRefreshToken();
            return ResponseEntity.ok(new JwtResponse(newAccessToken, newRefreshToken));
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired refresh token");
        }
    }
    private String extractRefreshToken(HttpServletRequest request) {
        // Retrieve the refresh token from the request attributes or parameters
        String refreshToken = request.getParameter("refreshToken");
        if (refreshToken != null && !refreshToken.isEmpty()) {
            return refreshToken;
        } else {
            // If the refresh token is not found in parameters, try retrieving it from headers
            refreshToken = request.getHeader("Authorization");
            if (refreshToken != null && refreshToken.startsWith("access ")) {
                // Extract the token from the Authorization header
                refreshToken = refreshToken.substring(7);
                return refreshToken;
            }
        }
        return null; // Return null if refresh token is not found in both parameters and headers
    }

    @RequestMapping(value = "/signup/employee/{roleName}", method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@Validated @RequestBody User user1,@PathVariable ("roleName")String roleName) {
       return userServiceIMP.registerUser(user1,  roleName);
    }

        @RequestMapping(value = "/signup/entreprise", method = RequestMethod.POST)
        public ResponseEntity<User> registerEntreprise(@Validated @RequestBody User user1){
          return userServiceIMP.registerEntreprise(user1);
    }
    @RequestMapping(value = "/signupadmin", method = RequestMethod.POST)
    public ResponseEntity<User> registerAdmin(@Valid @RequestBody User user)  {
        return userServiceIMP.registerAdmin(user);
    }
}
















