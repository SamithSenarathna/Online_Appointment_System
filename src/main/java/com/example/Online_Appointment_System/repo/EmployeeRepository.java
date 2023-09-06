package com.example.Online_Appointment_System.repo;

import com.example.Online_Appointment_System.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT * FROM employee WHERE branch LIKE %?1%", nativeQuery = true)
    List<Employee> search(String keyword);
}
