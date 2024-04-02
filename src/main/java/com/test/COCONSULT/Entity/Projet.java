package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjet;
    private String projectTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "projet")
    private Set<Activity> activities;
    @JsonIgnore
    @OneToMany(mappedBy = "projets")
    private Set<Meetings> meetings;

}
