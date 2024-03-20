package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Role;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.EditPofileRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserService {
    Optional<User> updateProfile(EditPofileRequest user);
    Optional<User> getUserById(Long id);
}
