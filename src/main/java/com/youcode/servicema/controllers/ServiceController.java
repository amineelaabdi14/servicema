package com.youcode.servicema.controllers;

import com.youcode.servicema.dto.requests.ServiceDto;
import com.youcode.servicema.services.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    @PostMapping
    public ResponseEntity addService(@RequestBody ServiceDto serviceDto) {
        serviceService.addService(serviceDto);
        
        return ResponseEntity.ok("Service created successfully");
    }
}
