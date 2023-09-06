package com.example.Online_Appointment_System.model;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@Data
@Table(name="appointments")
public class Appointments {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    private String joType;
    private String consultantName;

    private String date;

    private String status;
    private String branch;
    private String customerName;

}
