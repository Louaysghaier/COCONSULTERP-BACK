package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Qestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuest;
    private int ponderation;
    private String content;
    private boolean answer;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    Theme theme;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    Quiz quiz;
    @OneToMany(cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
    @ToString.Exclude
    List<Choice> choice;



}
