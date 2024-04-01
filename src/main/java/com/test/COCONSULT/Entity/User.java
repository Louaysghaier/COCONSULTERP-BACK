package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
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
    private Boolean blocked;
    private boolean valid;
    private String token;
    private Date joinDate;
    private double soldeConge;


    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<GroupChat> groupChats = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude

    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set <Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude

    private Set<Tickets> tickets=new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany (mappedBy = "userClient")
    private Set<Quote> quotes=new HashSet<>();

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Salaire> salaires;

    @OneToMany
    @JsonIgnore
    private Set<Conge> conges;

    @OneToMany
    @JsonIgnore
    private Set<Pointage> pointages;

    @OneToMany
    @JsonIgnore
    private Set<Evaluation> evaluations;
    @JsonIgnore
    @ToString.Exclude


    @OneToOne
    private ProjFeed projFeeds;

    public User(String name, String username, String email, String password, boolean blocked, String address, boolean valid) {

        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.address = address;
        this.valid = valid;

    }

    public void addMonthlyConge() {
        Date currentDate = new Date();
        long diffInMillies = Math.abs(currentDate.getTime() - joinDate.getTime());
        long diffInMonths = (diffInMillies / (1000 * 60 * 60 * 24 * 30));

        // Removed the multiplication by diffInMonths
        double additionalConge = 1.5;
        soldeConge += additionalConge;
    }


}
