package com.dreamjob.repository;

import com.dreamjob.model.Candidate;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Petrov Petr",
                "bachelors degree at CS", LocalDateTime.now(), 0));
        save(new Candidate(0, "Alexandrov Alexandr",
                "3+ years experience in backend", LocalDateTime.now(), 0));
        save(new Candidate(0, "Ivanov Ivan",
                "good soft manager skills", LocalDateTime.now(), 0));
        save(new Candidate(0, "Andreev Andrey",
                "worked in google 2 years", LocalDateTime.now(), 0));
        save(new Candidate(0, "Borisov Boris",
                "10+ experience in android developer", LocalDateTime.now(), 0));
        save(new Candidate(0, "Viktorov Viktor",
                "Have experience about 6 years have lead skills", LocalDateTime.now(), 0));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId.incrementAndGet());
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
                        candidate.getCreationDate(),
                        candidate.getFileId())) != null;
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