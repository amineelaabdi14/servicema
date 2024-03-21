package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.Authority;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import com.youcode.servicema.repositories.AuthorityRepository;
import com.youcode.servicema.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getAllByName(List<AuthorityEnum> authorities) {
        List<String> usersAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return authorityRepository.findAllByName(authorities);
    }
    @Override
    public Optional<Authority> getByName(AuthorityEnum authorityEnum) {
        
        return authorityRepository.findByName(authorityEnum);
    }

    @Override
    public Optional<Authority> getById(Long id) {
        return authorityRepository.findById(id);
    }
}
