package com.youcode.servicema.repositories;

import com.youcode.servicema.domain.entities.Service;
import com.youcode.servicema.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    public List<Service> findAllByTitle(String title);
    public List<Service> findAllByUser(User user);

}
