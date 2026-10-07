package com.roshan.monitoringplatform.dto;

import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitoredServiceRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private monitoredServiceEntity.Environment environment;
}
