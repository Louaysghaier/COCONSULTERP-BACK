package com.test.COCONSULT.Entity;

import com.test.COCONSULT.DTO.Priorite;
import com.test.COCONSULT.DTO.TypeContact;
import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Repertoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContact ;

    @Enumerated(EnumType.STRING)
    private TypeContact typeContact ;

    @Enumerated(EnumType.STRING)
    private Priorite priorite ;

    private int NumTel ;
    private String email ;

}
