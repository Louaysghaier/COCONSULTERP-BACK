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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTimeRec;

    @NotNull
    private Double budget;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalDate duration;
    @NotNull
    private String description;

    @JsonIgnore
    @OneToOne
    private Projets projets;

}
