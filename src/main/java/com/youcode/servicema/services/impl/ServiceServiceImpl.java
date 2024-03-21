package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.ServiceDto;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.ServiceRepository;
import com.youcode.servicema.services.CategoryService;
import com.youcode.servicema.services.ServiceService;
import com.youcode.servicema.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    @Override
    public Service addService(ServiceDto service) {
        if (!categoryService.getCategoryById(service.getCategoryId()).isPresent()){
            throw new CustomException("Category not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Service myService = ServiceDto.toService(service,currentUser);
        serviceRepository.save(myService);
        return myService;
    }

    @Override
    public Service updateService(ServiceDto service, Long id) {
        if (!serviceRepository.findById(id).isPresent()){
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        if (!categoryService.getCategoryById(service.getCategoryId()).isPresent()){
            throw new CustomException("Category not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Service myService = ServiceDto.toService(service,currentUser);
        myService.setId(id);
        serviceRepository.save(myService);
        return myService;
    }

    @Override
    public void deleteService(Long id) {
        if (!serviceRepository.findById(id).isPresent()){
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        serviceRepository.deleteById(id);
    }

    @Override
    public List<Service> getServices(String searchKeyword) {
        if (searchKeyword != null){
            return serviceRepository.findAllByTitle(searchKeyword);
        }
        return serviceRepository.findAll();
    }
}
