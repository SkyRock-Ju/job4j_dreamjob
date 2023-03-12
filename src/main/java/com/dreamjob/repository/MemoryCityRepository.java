package com.dreamjob.repository;

import com.dreamjob.model.City;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
@Repository
public class MemoryCityRepository implements CityRepository {
    private final Map<Integer, City> cities = new HashMap<>() {
        {
            put(1, new City(1, "Москва"));
            put(2, new City(2, "Санкт-Петербург"));
            put(3, new City(3, "Екатеринбург"));
        }
    };

    @Override
    public Collection<City> findAll() {
        return cities.values();
    }

}

