package com.youcode.servicema.repositories;

import com.youcode.servicema.domain.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
