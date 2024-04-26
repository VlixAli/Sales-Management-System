package com.pluralsight.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "action_type")
public class ActionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_en")
    @NotNull
    private String nameEn;

    @Column(name = "name_ar")
    @NotNull
    private String nameAr;

    @Column(name = "message_temp_en")
    private String messageTempEn;

    @Column(name = "message_temp_ar")
    private String messageTempAr;

    @OneToMany(mappedBy = "actionType", cascade = CascadeType.ALL)
    private List<Action> actions;


}
