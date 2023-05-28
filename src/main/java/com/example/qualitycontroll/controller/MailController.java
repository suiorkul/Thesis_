package com.example.qualitycontroll.controller;

import com.example.qualitycontroll.service.MailService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @GetMapping("/send/{analysisId}")
    public String send(Model model, @PathVariable Long analysisId) {
        model.addAttribute("analysis", mailService.send(analysisId));
        return "mail/success";
    }
}