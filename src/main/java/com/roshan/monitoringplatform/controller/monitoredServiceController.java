package com.roshan.monitoringplatform.controller;

import com.roshan.monitoringplatform.Entity.monitoredServiceEntity;
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
      public ResponseEntity<?> createService(@RequestBody monitoredServiceEntity serviceEntity){

          return monitoredService.createService(serviceEntity);
      }

      @GetMapping("/viewAllServices")
      public  ResponseEntity<List<monitoredServiceEntity>> viewAllServices(){

            List<monitoredServiceEntity> services = monitoredService.viewAllService();
            return ResponseEntity.ok(services);
      }

      @DeleteMapping("/delete/{uuid}")
      public ResponseEntity<String> deleteService(@PathVariable UUID uuid ){
             monitoredService.deleteService(uuid);
             return  ResponseEntity.ok("Deleted");

      }

}
