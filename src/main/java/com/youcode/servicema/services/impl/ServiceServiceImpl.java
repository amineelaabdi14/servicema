package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Category;
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
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    @Override
    public Service addService(ServiceDto service) {
        Optional<Category> category = categoryService.getCategoryById(service.getCategoryId());
        if (!category.isPresent()){
            throw new CustomException("Category not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Service myService = ServiceDto.toService(service,currentUser,category.get());
        serviceRepository.save(myService);
        return myService;
    }

    @Override
    public Service updateService(ServiceDto service, Long id) {
        if (!serviceRepository.findById(id).isPresent()){
            throw new CustomException("Service not found", HttpStatus.NOT_FOUND);
        }
        Optional<Category> category = categoryService.getCategoryById(service.getCategoryId());

        if (category.isPresent()){
            throw new CustomException("Category not found", HttpStatus.NOT_FOUND);
        }
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Service myService = ServiceDto.toService(service,currentUser,category.get());
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

    @Override
    public Optional<Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }
    @Override
    public Service findById(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }
    @Override
    public List<Service> getServicesByCurrentUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return serviceRepository.findAllByUser(currentUser);
    }

}
