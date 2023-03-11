package com.dreamjob.repository;

import com.dreamjob.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VacancyRepository {

    Vacancy save(Vacancy vacancy);

    boolean deleteById(UUID id);

    boolean update(Vacancy vacancy);

    Optional<Vacancy> findById(UUID id);

    Collection<Vacancy> findAll();

}