package com.dreamjob.repository;

import com.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MemoryCandidateRepository implements CandidateRepository {

    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private final Map<UUID, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate("Petrov Petr",
                "bachelors degree at CS", LocalDateTime.now()));
        save(new Candidate("Alexandrov Alexandr",
                "3+ years experience in backend", LocalDateTime.now()));
        save(new Candidate("Ivanov Ivan",
                "good soft manager skills", LocalDateTime.now()));
        save(new Candidate("Andreev Andrey",
                "worked in google 2 years", LocalDateTime.now()));
        save(new Candidate("Borisov Boris",
                "10+ experience in android developer", LocalDateTime.now()));
        save(new Candidate("Viktorov Viktor",
                "Have experience about 6 years have lead skills", LocalDateTime.now()));
    }

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        CompletableFuture.runAsync(() -> candidates.put(candidate.getId(), candidate));
        return candidate;
    }

    @Override
    public boolean deleteById(UUID id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) throws ExecutionException, InterruptedException {
        if (!candidates.containsKey(candidate.getId())) {
            return false;
        }
        CompletableFuture<Boolean> updateCandidateFuture = CompletableFuture.supplyAsync(() -> {
            candidates.put(candidate.getId(), candidate);
            return true;
        });
        return updateCandidateFuture.get();
    }

    @Override
    public Optional<Candidate> findById(UUID id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }

}