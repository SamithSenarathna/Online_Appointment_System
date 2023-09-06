package com.example.Online_Appointment_System.service;

import com.example.Online_Appointment_System.model.Appointments;
import com.example.Online_Appointment_System.repo.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {

    @Autowired
    AppointmentsRepository appointmentsRepository;

    public List<Appointments> viewAllBookings() {
        return appointmentsRepository.findAll();
    }
    public void addBookings(Appointments orders) {
        appointmentsRepository.save(orders);
    }

    public void deleteBookings(int id) {
        appointmentsRepository.deleteById(id);
    }

    public Optional<Appointments> getBookingsById(int id) {
        return appointmentsRepository.findById(id);
    }

    public List<Appointments> lisAllBookings(String keyword) {
        if (keyword != null) {
            return appointmentsRepository.search(keyword);
        }else {
            return viewAllBookings();
        }
    }

    public List<Appointments> lisAllEmployeeBookings(String keyword) {
        if (keyword != null) {
            return appointmentsRepository.searchEmployee(keyword);
        }else {
            return viewAllBookings();
        }
    }

}
