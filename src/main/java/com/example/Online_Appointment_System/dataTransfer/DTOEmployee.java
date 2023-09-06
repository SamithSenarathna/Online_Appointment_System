package com.example.Online_Appointment_System.dataTransfer;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class DTOEmployee {

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
