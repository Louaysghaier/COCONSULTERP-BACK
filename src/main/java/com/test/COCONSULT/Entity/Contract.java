package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContract ;
    private String Description ;
    private LocalDate DateContract ;
    private double Montant ;
    private int NbreTrnache ;
    private String Etape ;

    @JsonIgnore
    @OneToMany (mappedBy = "contracts")
    private List<Payment> payments ;
    @JsonIgnore
    @ManyToOne
    private Projets projets ;

}
