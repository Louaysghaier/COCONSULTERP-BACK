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


public class Cursus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id_cursus;
    private String experience ;
    private String skills ;
    private String Rendement;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany
    List<User> userr = new ArrayList<>();



}
