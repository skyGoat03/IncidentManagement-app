package com.roshan.monitoringplatform.Entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class monitoredServiceEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.UUID)
 private UUID id;

 @Column(nullable = false)
 private String name;

 private String description;

 @Enumerated(EnumType.STRING)
 @Column(nullable = false)
 private Environment environment;

 @Column(nullable = false)
 private Boolean active;

 @Column(nullable = false , name = "createdAt" , updatable = false)
 private Instant createdAt;


 public enum Environment{
   DEV , STAGING , PROD
 }
}
