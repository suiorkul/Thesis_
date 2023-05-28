package com.example.qualitycontroll.service;

import com.example.qualitycontroll.dal.entity.Analysis;
import com.example.qualitycontroll.dal.repository.AnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;
    private final AnalysisRepository analysisRepository;

    public Analysis send(Long analysisId) {
        Analysis analysis = analysisRepository.findById(analysisId).get();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(analysis.getPatient().getEmail());
        message.setSubject("your analysis");
        message.setText("получи анализ");
        emailSender.send(message);
        return analysis;
    }
}
