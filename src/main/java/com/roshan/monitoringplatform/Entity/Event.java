package com.roshan.monitoringplatform.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private monitoredServiceEntity monitoredService;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventSeverity severity;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false, updatable = false)
    private Instant occurredAt;

    @Column(nullable = false, updatable = false)
    private Instant receivedAt;

    public enum EventType {
        ERROR,
        HEALTH_CHECK,
        AVAILABILITY,
        CUSTOM
    }

    public enum EventSeverity {
        INFO,
        WARN,
        ERROR,
        CRITICAL
    }

    @PrePersist
    protected void onCreate() {
        if (receivedAt == null) {
            receivedAt = Instant.now();
        }
    }


}