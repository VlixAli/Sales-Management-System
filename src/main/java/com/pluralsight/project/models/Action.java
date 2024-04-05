package com.pluralsight.project.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "actions")
public class Action {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id ;

  @Column(name = "description_ar")
   private String descriptionAr ;

  @Column(name = "description_en")
   private String descriptionEn ;

  @Column(name = "action_time")
  @CreationTimestamp
  private Date actionTime;

  @Column(name = "trace_id")
   private String traceId;

   @ManyToOne
   @JoinColumn(name= "user_id")
   private User user;

  @ManyToOne
  @JoinColumn(name= "action_type_id")
  private ActionType actionType;

  @ManyToOne
  @JoinColumn(name= "application_id")
  private Application application;

  @ManyToOne
  @JoinColumn(name= "BE_id")
  private BE be;

  @OneToMany(mappedBy = "action", cascade = CascadeType.ALL)
  private List<Param> params;


}
