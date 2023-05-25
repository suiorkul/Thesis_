package com.example.qualitycontroll.service;

import com.example.qualitycontroll.dal.entity.Appointment;
import com.example.qualitycontroll.dal.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService extends AbstractService<Appointment, Long>{
    private final AppointmentRepository appointmentRepository;

    @Override
    protected JpaRepository<Appointment, Long> getRepository() {
        return appointmentRepository;
    }
}
