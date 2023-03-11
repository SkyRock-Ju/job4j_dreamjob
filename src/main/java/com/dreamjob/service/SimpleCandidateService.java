package com.dreamjob.service;

import com.dreamjob.model.Candidate;
import com.dreamjob.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class SimpleCandidateService implements CandidateService {

    private final CandidateRepository candidateRepository;

    public SimpleCandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
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