package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorityService {
    List<Authority> getAllByName(List<AuthorityEnum> authorities);
    Optional<Authority> getByName(AuthorityEnum authorityEnum);

    Optional<Authority> getById(Long id);
}

