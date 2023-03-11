package com.dreamjob.service;

import com.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface CandidateService {

    Candidate save(Candidate candidate);

    boolean deleteById(UUID id);

    boolean update(Candidate candidate) throws ExecutionException, InterruptedException;

    Optional<Candidate> findById(UUID id);

    Collection<Candidate> findAll();

}