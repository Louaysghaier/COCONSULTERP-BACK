package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.TypeMeet;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Meetings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeeting;
    private Date dateMeeting;
    @Enumerated(EnumType.STRING)
    private TypeMeet typeMeet;
    @ManyToOne
    @JsonIgnore
    private Projet projets;

}
