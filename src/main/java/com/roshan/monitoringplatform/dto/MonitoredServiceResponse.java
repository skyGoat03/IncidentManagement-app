package com.roshan.monitoringplatform.dto;


import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class MonitoredServiceResponse {

    private UUID id;
    private String name;
    private String description;
    private monitoredServiceEntity.Environment environment;
    private Boolean active;
    private Instant createdAt;

}
