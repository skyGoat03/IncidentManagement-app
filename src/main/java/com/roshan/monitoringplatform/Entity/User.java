package com.roshan.monitoringplatform.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String passwordHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Roles role;


    @Column(nullable = false , name = "createdAt" , updatable = false)
    public Instant createdAt;


    public enum Roles{
        ADMIN, USER
    }
}
