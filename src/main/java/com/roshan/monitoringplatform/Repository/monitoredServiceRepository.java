package com.roshan.monitoringplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;

import java.util.UUID;

public interface monitoredServiceRepository
        extends JpaRepository<monitoredServiceEntity, UUID> {

    boolean existsByNameAndEnvironment(String name , monitoredServiceEntity.Environment environment);
}