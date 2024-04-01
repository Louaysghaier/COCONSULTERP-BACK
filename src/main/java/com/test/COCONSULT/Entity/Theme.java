package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_theme ;
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "theme",cascade = CascadeType.ALL , fetch=FetchType.EAGER)
    @ToString.Exclude
    List<Qestion> question;


}
