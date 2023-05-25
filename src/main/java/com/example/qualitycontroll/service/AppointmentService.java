package com.example.qualitycontroll.service;

import com.example.qualitycontroll.dal.entity.Appointment;
import com.example.qualitycontroll.dal.entity.Patient;
import com.example.qualitycontroll.dal.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService extends AbstractService<Appointment, Long>{
    private final AppointmentRepository appointmentRepository;

    private static final int PAGE_SIZE = 10;

    public Page<Appointment> getByPatientOrDoctor(Patient patient, Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "created");

        return getRepository().findAll(pageRequest);
    }

    @Override
    protected JpaRepository<Appointment, Long> getRepository() {
        return appointmentRepository;
    }
}
