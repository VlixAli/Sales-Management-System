package com.pluralsight.project.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "actions")
@SQLDelete(sql = "Update actions set deleted = true where id=?")
@SQLRestriction("deleted=false")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_ar")
    private String descriptionAr;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "action_time")
    @CreationTimestamp
    private Date actionTime;

    @Column(name = "trace_id")
    @NotNull
    private String traceId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "action_type_id", referencedColumnName = "id")
    @NotNull
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "BE_id", referencedColumnName = "id")
    private BE be;

    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL)
    private List<Param> params;

    private boolean deleted = Boolean.FALSE;

}
