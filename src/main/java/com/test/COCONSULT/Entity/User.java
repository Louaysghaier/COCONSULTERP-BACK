package com.test.COCONSULT.Entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private int number;
    private boolean blocked;
    private boolean valid;
    private String token;
    private boolean bannedchatGP;
  //  private Date joinDate;
  //  private double soldeConge;
    private String exp;
    private byte[] image;
    private boolean addedtoGPChat;
   /* private Boolean disponible = false;
    private LocalDateTime signInTime;
    private LocalDateTime signOutTime;
    private Long sessionDuration;*/
    private LocalDateTime createdDate;
    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    @JsonIgnore

    private Set<GroupChat> groupChats = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @ToString.Exclude

    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    //@JsonIgnore

    private Set <Role> roles = new HashSet<>();





    public User(String name, String username, String email, String password, boolean blocked, String address, boolean valid) {

        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.address = address;
        this.valid = valid;

    }
   /* public void addMonthlyConge() {
        Date currentDate = new Date();
        long diffInMillies = Math.abs(currentDate.getTime() - joinDate.getTime());
        long diffInMonths = (diffInMillies / (1000 * 60 * 60 * 24 * 30));

        // Removed the multiplication by diffInMonths
        double additionalConge = 1.5;
        soldeConge += additionalConge;
    }*/


}
