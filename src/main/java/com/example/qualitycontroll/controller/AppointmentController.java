package com.example.qualitycontroll.controller;

import com.example.qualitycontroll.config.ModelConfig;
import com.example.qualitycontroll.dal.entity.Appointment;
import com.example.qualitycontroll.dal.entity.Patient;
import com.example.qualitycontroll.dal.enums.Role;
import com.example.qualitycontroll.dal.repository.AppointmentRepository;
import com.example.qualitycontroll.dal.repository.DepartmentRepository;
import com.example.qualitycontroll.dal.repository.PatientRepository;
import com.example.qualitycontroll.dal.repository.UserRepository;
import com.example.qualitycontroll.service.AppointmentService;
import com.example.qualitycontroll.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final ModelConfig modelConfig;
    private final PatientRepository patientRepository;
    private final AppointmentService appointmentService;
    private final UserRepository userRepository;

    @GetMapping
    public String index() {
        return "redirect:/appointments/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        modelConfig.configCommonAttributes(model);
        Page<Appointment> page = appointmentService.getList(pageNumber);
        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "appointments/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        modelConfig.configCommonAttributes(model);
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", userRepository.findAllByRoleIs(Role.DOCTOR));
        model.addAttribute("patients", patientRepository.findAll());
        return "appointments/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        modelConfig.configCommonAttributes(model);
        model.addAttribute("appointment", appointmentService.get(id));
        model.addAttribute("doctors", userRepository.findAllByRoleIs(Role.DOCTOR));
        model.addAttribute("patients", patientRepository.findAll());
        return "appointments/form";
    }

    @PostMapping(value = "/save")
    public String save(Appointment appointment, final RedirectAttributes ra,
                       @RequestParam Long patientId,
                       @RequestParam Long doctorId) {
        appointment.setPatient(patientRepository.getPatientById(patientId));
        appointment.setDoctor(userRepository.findById(doctorId).get());
        appointmentService.save(appointment);
        ra.addFlashAttribute("successFlash", "User successfully saved");
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.delete(id);
        return "redirect:/appointments";
    }
}
