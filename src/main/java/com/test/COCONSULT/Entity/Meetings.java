package com.test.COCONSULT.Entity;

import com.test.COCONSULT.DTO.TypeMeet;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Meetings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeeting;
    private LocalDate dateMeeting;
    @Enumerated(EnumType.STRING)
    private TypeMeet typeMeet;
    @ManyToOne
    private Projets projets;

}
