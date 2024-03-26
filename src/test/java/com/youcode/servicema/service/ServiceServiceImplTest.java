package com.youcode.servicema.service;

import com.youcode.servicema.dto.requests.ServiceDto;
import com.youcode.servicema.exceptions.CustomException;
import com.youcode.servicema.repositories.ServiceRepository;
import com.youcode.servicema.services.CategoryService;
import com.youcode.servicema.services.UserService;
import com.youcode.servicema.services.impl.CategoryServiceImpl;
import com.youcode.servicema.services.impl.ServiceServiceImpl;
import com.youcode.servicema.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ServiceServiceImplTest {
    private ServiceServiceImpl serviceServiceImpl;
    private UserServiceImpl userService= Mockito.mock(UserServiceImpl.class);
    private ServiceRepository serviceRepository= Mockito.mock(ServiceRepository.class);
    private CategoryServiceImpl categoryService= Mockito.mock(CategoryServiceImpl.class);
    @BeforeEach
    public void init() {
        serviceServiceImpl = new ServiceServiceImpl(serviceRepository,categoryService,userService);
    }
    @Test
    public void testAddService() {
        ServiceDto serviceDto = ServiceDto.builder()
                .categoryId(1L)
                .build();
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.ofNullable(null));
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.ofNullable(null));
        assertThrows(CustomException.class, () -> serviceServiceImpl.addService(serviceDto));
    }
    @Test
    public void testUpdateService() {
        ServiceDto serviceDto = ServiceDto.builder()
                .categoryId(1L)
                .build();
        when(serviceRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.ofNullable(null));
        assertThrows(CustomException.class, () -> serviceServiceImpl.updateService(serviceDto,1L));
    }
    @Test
    public void testDeleteService() {
        when(serviceRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        assertThrows(CustomException.class, () -> serviceServiceImpl.deleteService(1L));
    }
}
