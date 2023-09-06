package com.example.Online_Appointment_System.controller;

import com.example.Online_Appointment_System.model.Appointments;
import com.example.Online_Appointment_System.repo.AppointmentsRepository;
import com.example.Online_Appointment_System.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    AppointmentsService appointmentsService;
    @Autowired
    AppointmentsRepository appointmentsRepository;

    @GetMapping("/employee")
    public String driverHome() {
        return "employeePage";
    }

    @GetMapping("/employee/viewAppointments")
    public String bookings(Model model, @Param("keyword") String keyword) {
        List<Appointments> orders = appointmentsService.lisAllEmployeeBookings(keyword);
        model.addAttribute("orders", orders);
        model.addAttribute("keyword", keyword);
        return "employeeViewAppointments";
    }

    @PostMapping("/employee/updateAppointment/{id}")
    public String addBooking(@ModelAttribute("book") Appointments orders, HttpServletRequest request) throws ServletException {
        appointmentsRepository.save(orders);
        return "redirect:/employee";
    }

}
