package com.example.Online_Appointment_System.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String employee_id;

    private String branch;

    private String job_id;

    private String job_type;

    private String address;

    private String mobileNumber;

    private String imageName;

}
