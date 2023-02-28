package com.example.qualitycontroll.service;

import com.example.qualitycontroll.dal.entity.Patient;
import com.example.qualitycontroll.dal.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService extends AbstractService<Patient, Long>{

    private final PatientRepository patientRepository;

    @Override
    protected JpaRepository<Patient, Long> getRepository() {
        return patientRepository;
    }
}
