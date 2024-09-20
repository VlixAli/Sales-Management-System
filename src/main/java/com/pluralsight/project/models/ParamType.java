package com.pluralsight.project.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "name_en", nullable = false)
    private String nameEn ;

    @Column(name = "name_ar")
    private String nameAr;

    @OneToMany(mappedBy = "paramType",cascade = CascadeType.ALL)
    private List<Param> params;

}
