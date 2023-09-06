package com.example.Online_Appointment_System.service;

import com.example.Online_Appointment_System.model.Employee;
import com.example.Online_Appointment_System.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    public List<Employee> showAllEmployees() {
        return employeeRepository.findAll();
    }
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> lisAll(String keyword) {
        if (keyword != null) {
            return employeeRepository.search(keyword);
        }else {
            return showAllEmployees();
        }
    }
}
