package com.test.COCONSULT.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ansewrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ansewrs;
    private  int note ;




}
