package com.pluralsight.project.models;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="param_type")
public class ParamType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name_en")
    private String nameEn ;

    @Column(name = "name_ar")
    private String nameAr;

    @OneToMany(mappedBy = "paramType",cascade = CascadeType.ALL)
    private List<Param> params;

}
