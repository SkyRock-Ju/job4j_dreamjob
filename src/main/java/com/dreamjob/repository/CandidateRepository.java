package com.dreamjob.repository;

import com.dreamjob.model.Candidate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Repository
public interface CandidateRepository {
    Candidate save(Candidate candidate);

    boolean deleteById(UUID id);

    boolean update(Candidate candidate) throws ExecutionException, InterruptedException;

    Optional<Candidate> findById(UUID id);

    Collection<Candidate> findAll();

}
