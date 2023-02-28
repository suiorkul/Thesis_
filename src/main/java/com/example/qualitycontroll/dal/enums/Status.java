package com.example.qualitycontroll.dal.enums;

import lombok.Getter;

@Getter
public enum Status {

    REGISTERED("Пациент катталган"),
    SENT("Анализ Түркияга жөнөтүлдү"),
    RECEIVED("Түркиядагы дарыгерлерден жооп алынды"),
    NOTIFIED("Пациентке анализ берилди");

    public final String label;

    Status(String label) {
        this.label = label;
    }
}
