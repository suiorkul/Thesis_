package com.example.qualitycontroll.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Configuration
@RequiredArgsConstructor
public class ModelConfig {
    private final HttpServletRequest request;
    public void configCommonAttributes(Model model) {
        model.addAttribute("name", getUsername(request));
    }

    private String getUsername(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }
}
