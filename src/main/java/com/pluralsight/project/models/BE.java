package com.pluralsight.project.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "bes")
public class BE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String name;

    @OneToMany(mappedBy = "be", cascade = CascadeType.MERGE)
    private List<Action> actions;


}
