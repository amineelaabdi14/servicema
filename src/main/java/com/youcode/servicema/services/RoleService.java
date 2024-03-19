package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {

    Role save(Role role, boolean isSeed);

    Optional<Role> findDefaultRole();

    Optional<Role> getById(Long id);

    Optional<Role> getByName(String name);

    void delete(Long id);

    List<Role> getAll();

    Role grantAuthorities(Long authorityId, Long roleId);

    Role revokeAuthorities(Long authorityId, Long roleId);

}