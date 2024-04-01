package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjFeed  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPjtFeed;
    private String content;
    private String timeUpd;
    private String fctsUpd;

    @JsonIgnore
    @ManyToMany
    private Set<Projets> projets;
    @JsonIgnore
    @OneToOne
    private User userConsultant;

}
