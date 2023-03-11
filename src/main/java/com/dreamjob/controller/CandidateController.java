package com.dreamjob.controller;

import com.dreamjob.repository.CandidateRepository;
import com.dreamjob.repository.MemoryCandidateRepository;
import com.dreamjob.service.CandidateService;
import com.dreamjob.service.SimpleCandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService = SimpleCandidateService.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", candidateService.findAll());
        return "candidates/list";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "candidates/create";
    }
}
