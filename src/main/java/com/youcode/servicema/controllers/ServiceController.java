package com.youcode.servicema.controllers;

import com.youcode.servicema.dto.requests.ServiceDto;
import com.youcode.servicema.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    @PostMapping
    public ResponseEntity addService(@RequestBody ServiceDto serviceDto) {
        serviceService.addService(serviceDto);

        return ResponseEntity.ok(new HashMap<>(){
            {
                put("message", "Service added successfully");
            }
        });
    }
    @PutMapping("/{id}")
    public ResponseEntity updateService(@RequestBody ServiceDto serviceDto, @PathVariable Long id) {
        serviceService.updateService(serviceDto ,id);
        return ResponseEntity.ok(new HashMap<>(){
            {
                put("message", "Service updated successfully");
            }
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok(new HashMap<>(){
            {
                put("message", "Service deleted successfully");
            }
        });
    }

    @GetMapping
    public ResponseEntity getServices(@RequestParam @Nullable String searchKeyword) {
        return ResponseEntity.ok(serviceService.getServices(searchKeyword));
    }
}
