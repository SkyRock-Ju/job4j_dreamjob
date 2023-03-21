package com.dreamjob.repository;

import com.dreamjob.model.City;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;

@ThreadSafe
public interface CityRepository {
    Collection<City> findAll();
}
