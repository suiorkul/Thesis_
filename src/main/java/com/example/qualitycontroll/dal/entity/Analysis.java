package com.example.qualitycontroll.dal.entity;

import com.example.qualitycontroll.dal.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "analysis")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Analysis extends AbstractModel<Long>{

    @ManyToOne
            @JoinColumn(name = "patient", referencedColumnName = "id")
    Patient patient;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    AwsDocument departmentDocument;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    AwsDocument documentFromTurkey;

    @Enumerated(EnumType.STRING)
    Status status;

}
