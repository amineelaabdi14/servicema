package com.youcode.servicema.services.impl;

import com.youcode.servicema.domain.entities.City;
import com.youcode.servicema.repositories.CityRepository;
import com.youcode.servicema.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    @Override
    public City getCityByName(String name) {
        return cityRepository.findByName(name).orElse(null);
    }
    @Override
    public Boolean saveAll(List<City> cities) {
        cityRepository.saveAll(cities);
        return true;
    }
}
