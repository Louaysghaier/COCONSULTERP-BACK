package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.Niveau;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id_condidat;
    private String email;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany
    List<Quiz> quiz = new ArrayList<>();

}
