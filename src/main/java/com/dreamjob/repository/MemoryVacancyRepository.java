package com.dreamjob.repository;

import com.dreamjob.model.Vacancy;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemoryVacancyRepository implements VacancyRepository {

    private final Map<Integer, Vacancy> vacancies = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "New at this job",
                LocalDateTime.now(), true, 1, 0));
        save(new Vacancy(0, "Junior Java Developer", "Have experience about 1 year it this job",
                LocalDateTime.now(), true, 2, 0));
        save(new Vacancy(0, "Junior+ Java Developer",
                "More than 1 year of experience but have to improve some hard skills",
                LocalDateTime.now(), true, 3, 0));
        save(new Vacancy(0, "Middle Java Developer",
                "Have experience about 3 years and great hard and soft skills",
                LocalDateTime.now(), true, 1, 0));
        save(new Vacancy(0, "Middle+ Java Developer",
                "More than 3 years of experience but have to improve lead skills",
                LocalDateTime.now(), true, 2, 0));
        save(new Vacancy(0, "Senior Java Developer",
                "Have experience about 6 years have lead skills",
                LocalDateTime.now(), true, 3, 0));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId.incrementAndGet());
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) ->
                new Vacancy(
                        oldVacancy.getId(),
                        vacancy.getTitle(),
                        vacancy.getDescription(),
                        vacancy.getCreationDate(),
                        vacancy.getVisible(),
                        vacancy.getCityId(),
                        vacancy.getFileId()))
                != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }

}
