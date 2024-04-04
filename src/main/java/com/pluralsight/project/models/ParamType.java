package com.pluralsight.project.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name="param_type")
public class ParamType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code ;

    @Column(name = "name_en")
    private String nameEn ;

    @Column(name = "name_ar")
    private String nameAr;

    @OneToMany(mappedBy = "paramType",cascade = CascadeType.ALL)
    private List<Param> params;

}
