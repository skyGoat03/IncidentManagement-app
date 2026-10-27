package com.roshan.monitoringplatform.Repository;


import com.roshan.monitoringplatform.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface eventRepository extends JpaRepository<Event, UUID> {

}
