package com.roshan.monitoringplatform.controller;

import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;
import com.roshan.monitoringplatform.dto.MonitoredServiceRequest;
import com.roshan.monitoringplatform.dto.MonitoredServiceResponse;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.roshan.monitoringplatform.service.monitoredServiceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/services")
public class monitoredServiceController {

      private final monitoredServiceService monitoredService;

      public monitoredServiceController(monitoredServiceService service){
            this.monitoredService = service;

      }

      @PostMapping("/create")
      public ResponseEntity<MonitoredServiceResponse> createService(@Valid  @RequestBody MonitoredServiceRequest request){

          MonitoredServiceResponse responseDTO = monitoredService.createService(request);
          return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
      }

      @GetMapping("/viewAllServices")
      public  ResponseEntity<List<MonitoredServiceResponse>> viewAllServices(){

            List<MonitoredServiceResponse> services = monitoredService.viewAllService();
            return ResponseEntity.ok(services);
      }

      @DeleteMapping("/delete/{uuid}")
      public ResponseEntity<String> deleteService(@PathVariable UUID uuid ){
             monitoredService.deleteService(uuid);
             return  ResponseEntity.ok("Deleted");

      }

      @GetMapping("/{uuid}")
    public ResponseEntity<MonitoredServiceResponse> viewService(@PathVariable UUID uuid){

          MonitoredServiceResponse service = monitoredService.viewOneSerice(uuid);

          return ResponseEntity.ok(service);

      }

}
