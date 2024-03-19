package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AuthorityService {
    List<Authority> getAllByName(List<AuthorityEnum> authorities);
    Optional<Authority> getByName(AuthorityEnum authorityEnum);

    Optional<Authority> getById(Long id);
}

