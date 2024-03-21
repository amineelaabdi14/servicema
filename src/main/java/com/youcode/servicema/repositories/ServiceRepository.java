package com.youcode.servicema.repositories;

import com.youcode.servicema.domain.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    public List<Service> findAllByTitle(String title);
}
