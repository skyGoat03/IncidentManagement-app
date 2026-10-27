package com.roshan.monitoringplatform.service;

import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;
import com.roshan.monitoringplatform.Exception.resourceNotFoundException;
import com.roshan.monitoringplatform.dto.EventRequest;
import com.roshan.monitoringplatform.dto.EventResponse;
import com.roshan.monitoringplatform.Entity.Event;
import com.roshan.monitoringplatform.Repository.eventRepository;
import com.roshan.monitoringplatform.Repository.monitoredServiceRepository;
import com.roshan.monitoringplatform.Repository.eventRepository;
import com.roshan.monitoringplatform.Repository.monitoredServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class eventService {

    private final eventRepository eventRepository;
    private final monitoredServiceRepository monitoredServiceRepository;


    public eventService(
            eventRepository eventRepository,
            monitoredServiceRepository monitoredServiceRepository, monitoredServiceService monitoredServiceService) {
        this.eventRepository = eventRepository;
        this.monitoredServiceRepository = monitoredServiceRepository;

    }

    @Transactional
    public EventResponse ingestEvent(EventRequest request) {

        // Find the monitored service
        monitoredServiceEntity monitoredService =
                monitoredServiceRepository.findById(request.getServiceId())
                        .orElseThrow(() ->
                                new resourceNotFoundException(
                                        "Monitored service not found: "
                                                + request.getServiceId()));

        // Create and populate the event
        Event event = new Event();
        event.setMonitoredService(monitoredService);
        event.setEventType(request.getEventType());
        event.setSeverity(request.getSeverity());
        event.setMessage(request.getMessage());
        event.setOccurredAt(request.getOccurredAt());

        // Save the event
        Event savedEvent = eventRepository.save(event);

        // Map entity to response DTO
        return new EventResponse(
                savedEvent.getId(),
                savedEvent.getMonitoredService().getId(),
                savedEvent.getEventType(),
                savedEvent.getSeverity(),
                savedEvent.getMessage(),
                savedEvent.getOccurredAt(),
                savedEvent.getReceivedAt()
        );
    }
}