package com.example.qualitycontroll.controller;

import com.example.qualitycontroll.config.ModelConfig;
import com.example.qualitycontroll.dal.entity.Analysis;
import com.example.qualitycontroll.dal.entity.Patient;
import com.example.qualitycontroll.dal.repository.AnalysisRepository;
import com.example.qualitycontroll.dal.repository.DepartmentRepository;
import com.example.qualitycontroll.dal.repository.PatientRepository;
import com.example.qualitycontroll.service.AnalysisService;
import com.example.qualitycontroll.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("analyses")
@RequiredArgsConstructor
public class AnalysisController {

    private final ModelConfig modelConfig;
    private final AnalysisService analysisService;
    private final PatientRepository patientRepository;

    @GetMapping
    public String index() {
        return "redirect:/analyses/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        modelConfig.configCommonAttributes(model);
        Page<Analysis> page = analysisService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("patients", patientRepository.findAll());

        return "analyses/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        modelConfig.configCommonAttributes(model);
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("analysis", new Analysis());
        return "analyses/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        modelConfig.configCommonAttributes(model);
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("analysis", analysisService.get(id));
        return "analyses/form";
    }

    @PostMapping(value = "/save")
    public String save(Analysis analysis, final RedirectAttributes ra, @RequestParam String fio) {
        String[] fios = fio.split(" ");
        analysis.setPatient(patientRepository.getPatientByFirstNameAndLastNameAndPatronymic(fios[0], fios[1], fios[2]));
        analysisService.save(analysis);
        ra.addFlashAttribute("successFlash", "User successfully saved");
        return "redirect:/analyses";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        analysisService.delete(id);
        return "redirect:/analyses";
    }

}
