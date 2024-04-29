package com.test.COCONSULT.Entity;
import lombok.*;
import com.test.COCONSULT.DTO.TypePayment;


import javax.naming.Name;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment ;
    @Enumerated(EnumType.STRING)
    private TypePayment PayMetho ;
    private Boolean Done ;

    private double Revenue  ;
    private LocalDate DatePayement ;

    private String Name ;

}
