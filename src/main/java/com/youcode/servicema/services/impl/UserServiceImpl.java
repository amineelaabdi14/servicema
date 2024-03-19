package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Role;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.repositories.RoleRepository;
import com.youcode.servicema.repositories.UserRepository;
import com.youcode.servicema.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Role grantRoleToUser(Long userId, Long roleId) {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        Role role = roleRepository.findById(roleId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (role != null && user != null) {
            user.setRole(role);
            userRepository.save(user);
            return role;
        }
        return null;
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }


}
