package com.roshan.monitoringplatform.service;

import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;
import com.roshan.monitoringplatform.Exception.resourceNotFoundException;
import com.roshan.monitoringplatform.Exception.serivceAlreadyExistsException;
import com.roshan.monitoringplatform.Repository.monitoredServiceRepository;
import com.roshan.monitoringplatform.dto.MonitoredServiceRequest;
import com.roshan.monitoringplatform.dto.MonitoredServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class monitoredServiceService {

    private monitoredServiceRepository repository;

    public monitoredServiceService(monitoredServiceRepository repository){
        this.repository = repository;
    }


    public MonitoredServiceResponse createService(MonitoredServiceRequest request){

        monitoredServiceEntity entity = new monitoredServiceEntity();
        entity.setName(request.getName());
        entity.setEnvironment(request.getEnvironment());
        entity.setDescription(request.getDescription());
        entity.setCreatedAt(Instant.now());
        entity.setActive(true);
        if(repository.existsByNameAndEnvironment(request.getName(), request.getEnvironment())){

            throw new serivceAlreadyExistsException("Service already exists for this");

        }
        else {
            monitoredServiceEntity savedEntity = repository.save(entity);
            MonitoredServiceResponse responseDto = new MonitoredServiceResponse();
            responseDto.setName(savedEntity.getName());
            responseDto.setId(savedEntity.getId());
            responseDto.setEnvironment(savedEntity.getEnvironment());
            responseDto.setDescription(savedEntity.getDescription());
            responseDto.setActive(savedEntity.getActive());
            responseDto.setCreatedAt(savedEntity.getCreatedAt());
            return responseDto;
        }

    }
    public List<MonitoredServiceResponse> viewAllService() {

        List<monitoredServiceEntity> entity = repository.findAll();
        List<MonitoredServiceResponse> responseDTO = new ArrayList<>();
        responseDTO = entity.stream()
                .map(Entity -> {
                    MonitoredServiceResponse dto = new MonitoredServiceResponse();
                    dto.setName(Entity.getName());
                    dto.setDescription(Entity.getDescription());
                    dto.setId(Entity.getId());
                    dto.setActive(Entity.getActive());
                    dto.setEnvironment(Entity.getEnvironment());
                    dto.setCreatedAt(Entity.getCreatedAt());
                    return dto;
                })
                .toList();
        return responseDTO;
    }
    public MonitoredServiceResponse viewOneSerice(UUID id) {


            Optional<monitoredServiceEntity> optionalEntity = repository.findById(id);
            if(optionalEntity.isEmpty()){
                throw new resourceNotFoundException("Service not found with the id"+ id);
            }

            monitoredServiceEntity entity = optionalEntity.get();
            MonitoredServiceResponse response = new MonitoredServiceResponse();
            response.setName(entity.getName());
            response.setDescription(entity.getDescription());
            response.setId(entity.getId());
            response.setActive(entity.getActive());
            response.setEnvironment(entity.getEnvironment());
            response.setCreatedAt(entity.getCreatedAt());
            return response;


    }

    public  ResponseEntity<?> deleteService(UUID id){
        repository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    public MonitoredServiceResponse changeState(UUID uuid , boolean state){

        monitoredServiceEntity entity = repository.findById(uuid).orElseThrow(()-> new resourceNotFoundException("Serivce does not exist"));

        entity.setActive(state);

        monitoredServiceEntity updatedEntity =  repository.save(entity);

        MonitoredServiceResponse response = new MonitoredServiceResponse();
        response.setName(updatedEntity.getName());
        response.setDescription(updatedEntity.getDescription());
        response.setId(updatedEntity.getId());
        response.setActive(updatedEntity.getActive());
        response.setEnvironment(updatedEntity.getEnvironment());
        response.setCreatedAt(updatedEntity.getCreatedAt());


        return response;

    }






}
