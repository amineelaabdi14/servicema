package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.dto.requests.ServiceDto;

import java.util.List;

public interface ServiceService {
    public Service addService(ServiceDto service);
    public Service updateService(ServiceDto service, Long id);
    public void deleteService(Long id);
    public List<Service> getServices(String searchKeyword);
}
