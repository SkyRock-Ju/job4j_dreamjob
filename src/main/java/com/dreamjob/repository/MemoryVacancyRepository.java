package com.dreamjob.repository;

import com.dreamjob.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class MemoryVacancyRepository implements VacancyRepository {

    private final Map<UUID, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy("Intern Java Developer",
                "New at this job", LocalDateTime.now()));
        save(new Vacancy("Junior Java Developer",
                "Have experience about 1 year it this job", LocalDateTime.now()));
        save(new Vacancy("Junior+ Java Developer",
                "More than 1 year of experience but have to improve some hard skills", LocalDateTime.now()));
        save(new Vacancy("Middle Java Developer",
                "Have experience about 3 years and great hard and soft skills", LocalDateTime.now()));
        save(new Vacancy("Middle+ Java Developer",
                "More than 3 years of experience but have to improve lead skills", LocalDateTime.now()));
        save(new Vacancy("Senior Java Developer",
                "Have experience about 6 years have lead skills", LocalDateTime.now()));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(UUID id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) ->
                new Vacancy(
                        oldVacancy.getId(),
                        vacancy.getTitle(),
                        vacancy.getDescription(),
                        vacancy.getCreationDate()))
                != null;
    }

    @Override
    public Optional<Vacancy> findById(UUID id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }

}
