package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.EtapeContract;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContract ;
    private String Description ;
    private LocalDate DateContract ;
    private double Montant ;
    private int NbreTranche ;

    @Enumerated(EnumType.STRING)
    private EtapeContract Etape ;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repertoire_id")
    private Repertoire repertoire;

    private String repertoireContact;


    public String getRepertoireContact() {
        return repertoireContact;
    }

    public void setRepertoireContact(String repertoireContact) {
        this.repertoireContact = repertoireContact;
    }


}