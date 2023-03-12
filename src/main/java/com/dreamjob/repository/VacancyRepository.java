package com.dreamjob.repository;

import com.dreamjob.model.Vacancy;
import net.jcip.annotations.ThreadSafe;
import java.util.Collection;
import java.util.Optional;

@ThreadSafe
public interface VacancyRepository {

    Vacancy save(Vacancy vacancy);

    boolean deleteById(int id);

    boolean update(Vacancy vacancy);

    Optional<Vacancy> findById(int id);

    Collection<Vacancy> findAll();

}