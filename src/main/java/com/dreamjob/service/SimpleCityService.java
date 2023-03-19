package com.dreamjob.service;

import com.dreamjob.model.City;
import com.dreamjob.repository.CityRepository;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;

import java.util.Collection;

@ThreadSafe
@Service
public class SimpleCityService implements CityService {

    private final CityRepository cityRepository;

    public SimpleCityService(CityRepository sql2oCityRepository) {
        this.cityRepository = sql2oCityRepository;
    }

    @Override
    public Collection<City> findAll() {
        return cityRepository.findAll();
    }
}