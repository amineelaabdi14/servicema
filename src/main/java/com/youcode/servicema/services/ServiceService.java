package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.dto.requests.ServiceDto;

public interface ServiceService {
    public Service addService(ServiceDto service);
}
