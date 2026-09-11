package com.roshan.monitoringplatform.service;

import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;
import com.roshan.monitoringplatform.Repository.monitoredServiceRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class monitoredServiceService {

    private monitoredServiceRepository repository;

    public monitoredServiceService(monitoredServiceRepository repository){
        this.repository = repository;
    }


    public ResponseEntity<?> createService(monitoredServiceEntity service){
        service.setCreatedAt(Instant.now());

        if(!repository.existsByNameAndEnvironment(service.getName(), service.getEnvironment())){
            monitoredServiceEntity savedService = repository.save(service);
            return ResponseEntity.ok(savedService);
        }
        else return ResponseEntity.badRequest().body("Already exists");

    }
    public List<monitoredServiceEntity> viewAllService(){
        return repository.findAll();
    }
    public ResponseEntity<monitoredServiceEntity> viewOneSerice(UUID id){

        return  repository.findById(id).map(ResponseEntity::ok).orElse( ResponseEntity.notFound().build());
    }

    public  ResponseEntity<?> deleteService(UUID id){
        repository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }





}
