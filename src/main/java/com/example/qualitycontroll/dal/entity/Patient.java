package com.example.qualitycontroll.dal.entity;

import com.example.qualitycontroll.dal.enums.Sex;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends AbstractModel<Long>{

    String firstName;

    String lastName;

    String patronymic;

    @Enumerated(EnumType.STRING)
    Sex sex;

    String email;

    String phoneNumber;

    String dateOfBirth;

    @ManyToOne()
            @JoinColumn(name = "department", referencedColumnName = "id")
    Department department;

}
