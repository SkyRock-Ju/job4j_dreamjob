package com.dreamjob.service;

import com.dreamjob.model.City;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;

@ThreadSafe
public interface CityService {
    Collection<City> findAll();
}
