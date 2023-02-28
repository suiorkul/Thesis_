package com.example.qualitycontroll.controller;

import com.example.qualitycontroll.config.ModelConfig;
import com.example.qualitycontroll.dal.entity.Patient;
import com.example.qualitycontroll.dal.entity.User;
import com.example.qualitycontroll.dal.repository.DepartmentRepository;
import com.example.qualitycontroll.dal.repository.PatientRepository;
import com.example.qualitycontroll.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("patients")
@RequiredArgsConstructor
public class PatientController {

    private final ModelConfig modelConfig;
    private final PatientService patientService;
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String index() {
        return "redirect:/patients/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        modelConfig.configCommonAttributes(model);
        Page<Patient> page = patientService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "patients/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        modelConfig.configCommonAttributes(model);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("patient", new Patient());
        return "patients/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        modelConfig.configCommonAttributes(model);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("patient", patientService.get(id));
        return "patients/form";
    }

    @PostMapping(value = "/save")
    public String save(Patient patient, final RedirectAttributes ra, @RequestParam String departmentName) {
        patient.setDepartment(departmentRepository.getDepartmentByName(departmentName));
        patientService.save(patient);
        ra.addFlashAttribute("successFlash", "User successfully saved");
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        patientService.delete(id);
        return "redirect:/patients";
    }

}
