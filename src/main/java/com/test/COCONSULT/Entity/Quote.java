package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    @Positive
    private Double montant;

    @NotNull
    private Date creationDate;

    @NotNull
    @Future
    private Date expireDate;

    @NotNull
    private String description;

    @NotNull
    private boolean isValid;



    @JsonIgnore
    @ManyToOne
   // @JoinColumn(name = "idProjet")
    private Projets projets;

    @JsonIgnore
    @ManyToOne
    User userClient;


    public int getYearOfCreationDate() {
        // Extract the year from the creationDate field
        // Note: This is a simple implementation. You may want to handle null cases or use a more robust approach.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.creationDate);
        return calendar.get(Calendar.YEAR);
    }

}

