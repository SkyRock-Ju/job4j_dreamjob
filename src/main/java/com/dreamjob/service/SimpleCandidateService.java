package com.dreamjob.service;

import com.dreamjob.model.Candidate;
import com.dreamjob.repository.CandidateRepository;
import com.dreamjob.repository.MemoryCandidateRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class SimpleCandidateService implements CandidateService{

    private static final SimpleCandidateService INSTANCE = new SimpleCandidateService();

    private final CandidateRepository candidateRepository = MemoryCandidateRepository.getInstance();

    public static SimpleCandidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public boolean deleteById(UUID id) {
        return candidateRepository.deleteById(id);
    }

    @Override
    public boolean update(Candidate candidate) throws ExecutionException, InterruptedException {
        return candidateRepository.update(candidate);
    }

    @Override
    public Optional<Candidate> findById(UUID id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidateRepository.findAll();
    }

}