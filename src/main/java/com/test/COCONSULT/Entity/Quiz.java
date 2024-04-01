package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_quiz;
    private String titre;


    private LocalDate dateQuiz;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany
    List<Candidat> candidatList = new ArrayList<>();
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "quiz" , fetch=FetchType.EAGER)
    List<Qestion> questionList = new ArrayList<>() ;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "quiz")
    List<Candidat> candidat = new ArrayList<>();

}
