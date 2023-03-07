package com.dreamjob.controller;

import com.dreamjob.repository.CandidateRepository;
import com.dreamjob.repository.MemoryCandidateRepository;
import com.dreamjob.repository.MemoryVacancyRepository;
import com.dreamjob.repository.VacancyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateRepository candidateRepository = MemoryCandidateRepository.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        return "candidates/list";
    }
}