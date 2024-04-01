package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQuote;
    private Double montant;
    private LocalDate creationDate;
    private LocalDate expireDate;
    private String description;


    @JsonIgnore
    @ManyToOne
    private Projets projets;

    @JsonIgnore
    @ManyToOne
    User userClient;


    // @ManyToOne
    // use elation with user
    //consultant consultants;


}

