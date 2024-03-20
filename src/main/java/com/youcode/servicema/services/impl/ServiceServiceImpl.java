package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.dto.requests.ServiceDto;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.ServiceRepository;
import com.youcode.servicema.services.CategoryService;
import com.youcode.servicema.services.ServiceService;
import com.youcode.servicema.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    @Override
    public Service addService(ServiceDto service) {
        if (!userService.getUserById(service.getUserId()).isPresent()){
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        if (!categoryService.getCategoryById(service.getCategoryId()).isPresent()){
            throw new CustomException("Category not found", HttpStatus.NOT_FOUND);
        }
        Service myService = ServiceDto.toService(service);
        serviceRepository.save(myService);
        return myService;
    }
}
