package com.test.COCONSULT.Entity;

import com.test.COCONSULT.DTO.Status;
import com.test.COCONSULT.DTO.TypeSalesActivity;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivitySalesTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActSale ;
    private LocalDate HeureStart ;
    private LocalDate HeureEnd ;
    private String Description ;
    @Enumerated(EnumType.STRING)
    private TypeSalesActivity TypeAct ;
    @Enumerated(EnumType.STRING)
    private Status status ;
}
