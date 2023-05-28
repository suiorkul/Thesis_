package com.example.qualitycontroll.util;


import com.example.qualitycontroll.dal.entity.Appointment;
import com.example.qualitycontroll.dal.entity.Patient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WhatsAppUtils {

    @Value("${link.url}")
    private String linkUrl;

    public String registerPatientMessage(Patient patient, String username, String password) {
        return String.format("Добро пожаловать %s %s!\n" +
                "Вы зарегистрированы на сайте нашей компании\n" +
                "Перейдите по ссылке и войдите в свой личный кабинет: %s\n" +
                "Логин: %s\n" +
                "Пароль: %s", patient.getFirstName(), patient.getLastName(), linkUrl, username, password);
    }

    public String cancelAppointmentMessage(Appointment appointment) {
        return String.format("Уважаемый %s %s\n" +
                        "Ваша запись на %s %s, к врачу %s %s отменена",
                appointment.getPatient().getFirstname(),
                appointment.getPatient().getLastname(), appointment.getDate(),
                appointment.getTime(), appointment.getDoctor().getFirstname(),
                appointment.getDoctor().getLastname());
    }

    ;

    public String registerAppointmentMessage(Appointment appointment) {
        return String.format("Уважаемый %s %s\n" +
                        "Вы записаны на %s %s, к врачу %s %s",
                appointment.getPatient().getFirstname(),
                appointment.getPatient().getLastname(), appointment.getDate(),
                appointment.getTime(), appointment.getDoctor().getFirstname(),
                appointment.getDoctor().getLastname());
    }

}
