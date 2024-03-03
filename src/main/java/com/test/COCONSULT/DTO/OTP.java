package com.test.COCONSULT.DTO;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OTP {
    String identification;
    Date expiredDate;
    @Id
    private Long id;


}
