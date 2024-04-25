package com.pluralsight.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "param")
public class Param {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    private Action action;

    @ManyToOne
    @JoinColumn(name = "param_type_id", referencedColumnName = "id")
    private ParamType paramType;

}
