package com.dreamjob.controller;

import com.dreamjob.repository.MemoryVacancyRepository;
import com.dreamjob.repository.VacancyRepository;
import com.dreamjob.service.SimpleVacancyService;
import com.dreamjob.service.VacancyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {

    private final VacancyService vacancyService = SimpleVacancyService.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("vacancies", vacancyService.findAll());
        return "vacancies/list";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "vacancies/create";
    }
}
