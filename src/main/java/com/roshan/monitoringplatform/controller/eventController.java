package com.roshan.monitoringplatform.controller;

import com.roshan.monitoringplatform.dto.EventRequest;
import com.roshan.monitoringplatform.dto.EventResponse;
import com.roshan.monitoringplatform.service.eventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events")
public class eventController {

    private final eventService eventService;

    public eventController(eventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> ingestEvent(
            @Valid @RequestBody EventRequest request) {

        EventResponse response = eventService.ingestEvent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
