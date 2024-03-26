package com.youcode.servicema.controllers;

import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.dto.requests.ServiceDto;
import com.youcode.servicema.dto.responses.ServiceResponse;
import com.youcode.servicema.services.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    @PreAuthorize("hasAuthority('ADD_SERVICE')")
    @PostMapping
    public ResponseEntity addService(@RequestBody @Valid ServiceDto serviceDto) {
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

    @PreAuthorize("hasAuthority('DELETE_SERVICE')")
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
        List<Service> services = serviceService.getServices(searchKeyword);
        return ResponseEntity.ok(ServiceResponse.fromServices(services));
    }

    @GetMapping("/{id}")
    public ResponseEntity getService(@PathVariable Long id) {
        Service service = serviceService.getServiceById(id).orElse(null);
        return ResponseEntity.ok(ServiceResponse.fromService(service));
    }

    @GetMapping("/currentUser")
        public ResponseEntity getServicesByCurrentUser() {
        List<Service> services = serviceService.getServicesByCurrentUser();
        return ResponseEntity.ok(ServiceResponse.fromServices(services));
    }
}
