package com.test.COCONSULT.Entity;


import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prospect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProspect ;
    private String Entreprise ;
    private String Email ;
    private String NumTel ;
    private LocalDate Date ;



}
