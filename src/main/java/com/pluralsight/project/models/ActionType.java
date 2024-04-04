package com.pluralsight.project.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "action_type")
public class ActionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String code;

    @Column(name = "name_eng")
    private String nameEng;

    @Column(name = "name_ar")
    private String nameAr ;

    @Column(name = "message_temp_eng")
    private String messageTempEng ;

    @Column(name = "message_temp_ar")
    private String messageTempAr;

    @OneToMany(mappedBy = "actionType", cascade = CascadeType.ALL)
    private List<Action> actions;


}
