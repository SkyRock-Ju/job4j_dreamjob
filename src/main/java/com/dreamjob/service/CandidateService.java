package com.dreamjob.service;

import com.dreamjob.model.Candidate;
import com.dreamjob.model.FileDto;
import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.Optional;

@ThreadSafe
public interface CandidateService {

    Candidate save(Candidate candidate, FileDto image);

    boolean deleteById(int id);

    boolean update(Candidate candidate, FileDto image);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();

}