package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String pdfFile;
    @JsonIgnore
    @ManyToMany
    List<Quiz> quiz = new ArrayList<>();
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
   JobOpport jobOpport;
    @JsonIgnore
    @OneToMany(mappedBy="candidat",cascade = CascadeType.ALL )
    List<test>  test= new ArrayList<>();


}
