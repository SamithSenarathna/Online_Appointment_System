package com.example.Online_Appointment_System.repo;

import com.example.Online_Appointment_System.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {
    @Query(value = "SELECT * FROM appointments WHERE customer_name LIKE %?1%", nativeQuery = true)
    List<Appointments> search(String keyword);

    @Query(value = "SELECT * FROM appointments WHERE consultant_name LIKE %?1%", nativeQuery = true)
    List<Appointments> searchEmployee(String keyword);
}
