package com.example.qualitycontroll.dal.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Админ"),
    DOCTOR("Доктур"),
    RECEPTIONIST("Рецепционист"),
    PATIENT("Пациент");

    private String label;

    Role(String label) {
        this.label = label;
    }
}
