package com.example.Online_Appointment_System.controller;

import com.example.Online_Appointment_System.model.Appointments;
import com.example.Online_Appointment_System.model.Employee;
import com.example.Online_Appointment_System.repo.AppointmentsRepository;
import com.example.Online_Appointment_System.service.AppointmentsService;
import com.example.Online_Appointment_System.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    AppointmentsRepository appointmentsRepository;
    @Autowired
    AppointmentsService appointmentsService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "index";
    }

    @RequestMapping("/viewJobs")
    public String manageEmployees(Model model, @Param("keyword1") String keyword) {
        List<Employee> listEmployee = employeeService.lisAll(keyword);
        model.addAttribute("products", listEmployee);
        model.addAttribute("keyword1", keyword);
        return "viewJobs";
    }

    @GetMapping("/job/viewJob/{id}")
    public String viewVehicle(Model model, @PathVariable int id) {
        model.addAttribute("product", employeeService.getEmployeeById(id).get());
        return "jobsById";
    }

    @PostMapping("/job/viewJob/{id}")
    public String addBooking(@ModelAttribute("book") Appointments orders, HttpServletRequest request) throws ServletException {
        appointmentsRepository.save(orders);
        return "redirect:/viewJobs";
    }

    @GetMapping("/job/appointments")
    public String viewBookings(Model model, @Param("keyword") String keyword) {
        List<Appointments> orders = appointmentsService.lisAllBookings(keyword);
        model.addAttribute("order", orders);
        model.addAttribute("keyword", keyword);
        return "viewAppointments";
    }

}
