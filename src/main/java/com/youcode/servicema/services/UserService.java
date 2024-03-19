package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Role;
import com.youcode.servicema.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserService {
    Optional<User> getById(Long id);
    Role grantRoleToUser(Long userId, Long roleId);
}
