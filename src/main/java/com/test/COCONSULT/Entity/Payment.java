package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.test.COCONSULT.DTO.TypePayment;


import javax.persistence.*;

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

    @JsonIgnore
    @ManyToOne
    private Contract contracts ;
}
