package com.roshan.monitoringplatform.dto;

import com.roshan.monitoringplatform.Entity.Event.EventSeverity;
import com.roshan.monitoringplatform.Entity.Event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class EventResponse {

    private UUID id;
    private UUID serviceId;
    private EventType eventType;
    private EventSeverity severity;
    private String message;
    private Instant occurredAt;
    private Instant receivedAt;

    public EventResponse() {
    }

    public EventResponse(
            UUID id,
            UUID serviceId,
            EventType eventType,
            EventSeverity severity,
            String message,
            Instant occurredAt,
            Instant receivedAt) {

        this.id = id;
        this.serviceId = serviceId;
        this.eventType = eventType;
        this.severity = severity;
        this.message = message;
        this.occurredAt = occurredAt;
        this.receivedAt = receivedAt;
    }
}

