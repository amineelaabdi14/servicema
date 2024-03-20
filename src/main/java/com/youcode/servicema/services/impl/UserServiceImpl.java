package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Role;
import com.youcode.servicema.domain.entities.User;
import com.youcode.servicema.dto.requests.EditPofileRequest;
import com.youcode.servicema.repositories.RoleRepository;
import com.youcode.servicema.repositories.UserRepository;
import com.youcode.servicema.services.CityService;
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
    private final CityService cityService;
    @Override
    public Optional<User> updateProfile(EditPofileRequest user) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        currentUser.setCity(cityService.getCityByName(user.getCity()));
        currentUser.setDescription(user.getDescription());
        currentUser.setName(user.getName());
        currentUser.setPhone(user.getPhone());
        return Optional.of(userRepository.save(currentUser));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

}
