package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Projets implements Serializable

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProjet;

    @NotNull
    @Size(min = 1, max = 255)
    private String projetTitle;

    @Positive
    private Double budget;

    private String Mail;
    @NotNull
    private LocalDate dateDebut;

    @NotNull
    @Future
    private LocalDate dateFin;

    @Positive
    private int effectif;
    private String description;

    public void setMail(String mail) {
        if (isValidEmail(mail)) {
            this.Mail = mail;
        } else {
            throw new IllegalArgumentException("Adresse email invalide.");
        }
    }
        private boolean isValidEmail(String email) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return email.matches(emailRegex);
        }

    @JsonIgnore
    @OneToMany (mappedBy = "projets")
    private List<Expanses> expanses;
    @JsonIgnore
    @OneToOne(mappedBy = "projets")
    private TimeRecord timeRecord;
    @JsonIgnore
    @OneToOne(mappedBy = "projets")
    private Assignements assignements;

    @JsonIgnore
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @JsonIgnore
    @OneToMany(mappedBy = "projets")
    private List<Meetings> meetings;

    @JsonIgnore
    @OneToMany(mappedBy = "projets")
    private List<Contract> contracts;


}
