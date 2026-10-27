package com.roshan.monitoringplatform.dto;

import com.roshan.monitoringplatform.Entity.Event;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class EventRequest {
    @NotNull(message = "Service ID is required")
    private UUID serviceId;

    @NotNull(message = "Event type is required")
    private Event.EventType eventType;

    @NotNull(message = "Severity is required")
    private Event.EventSeverity severity;

    @NotBlank(message = "Message cannot be empty")
    @Size(max = 2000, message = "Message cannot exceed 2000 characters")
    private String message;

    @NotNull(message = "Occurrence time is required")
    private Instant occurredAt;

    public UUID getServiceId() {
        return serviceId;
    }


}
