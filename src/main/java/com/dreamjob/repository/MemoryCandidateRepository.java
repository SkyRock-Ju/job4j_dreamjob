package com.dreamjob.repository;

import com.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryCandidateRepository implements CandidateRepository {

    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Petrov Petr",
                "bachelors degree at CS", LocalDateTime.now()));
        save(new Candidate(0, "Alexandrov Alexandr",
                "3+ years experience in backend", LocalDateTime.now()));
        save(new Candidate(0, "Ivanov Ivan",
                "good soft manager skills", LocalDateTime.now()));
        save(new Candidate(0, "Andreev Andrey",
                "worked in google 2 years", LocalDateTime.now()));
        save(new Candidate(0, "Borisov Boris",
                "10+ experience in android developer", LocalDateTime.now()));
        save(new Candidate(0, "Viktorov Viktor",
                "Have experience about 6 years have lead skills", LocalDateTime.now()));
    }

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldCandidate) ->
                new Candidate(
                        oldCandidate.getId(),
                        candidate.getTitle(),
                        candidate.getDescription(),
                        candidate.getCreationDate()))
                != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }

}