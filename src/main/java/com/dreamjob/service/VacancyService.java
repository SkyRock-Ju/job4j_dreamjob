package com.dreamjob.service;

import com.dreamjob.model.Vacancy;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface VacancyService {

    Vacancy save(Vacancy vacancy);

    boolean deleteById(UUID id);

    boolean update(Vacancy vacancy);

    Optional<Vacancy> findById(UUID id);

    Collection<Vacancy> findAll();

}