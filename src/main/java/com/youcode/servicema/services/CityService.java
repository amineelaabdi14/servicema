package com.youcode.servicema.services;

import com.youcode.servicema.domain.entities.City;

import java.util.List;

public interface CityService {
    City getCityByName(String name);
    Boolean saveAll(List<City> cities);
    String[] getCities();
}
