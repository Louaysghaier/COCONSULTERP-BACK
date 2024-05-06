package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expanses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExps;

    @NotNull
    private String category;

    @NotNull
    private Double montant;

    @NotNull
    private LocalDate date;

    @NotNull
    private String description;


    @JsonIgnore
    @ManyToOne
    Projets projets;

}
