package com.example.Online_Appointment_System.controller;

import com.example.Online_Appointment_System.dataTransfer.DTOEmployee;
import com.example.Online_Appointment_System.model.Appointments;
import com.example.Online_Appointment_System.model.Employee;
import com.example.Online_Appointment_System.model.Role;
import com.example.Online_Appointment_System.model.User;
import com.example.Online_Appointment_System.repo.RoleRepository;
import com.example.Online_Appointment_System.repo.UserRepository;
import com.example.Online_Appointment_System.service.AppointmentsService;
import com.example.Online_Appointment_System.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/admin/manageEmployees")
    public String manageEmployees(Model model, @Param("keyword") String keyword) {
        List<Employee> listDriver = employeeService.lisAll(keyword);
        model.addAttribute("employees", listDriver);
        model.addAttribute("keyword", keyword);
        return "manageEmployees";
    }

    @GetMapping("/admin/employee/add")
    public String getAddEmployee(Model model) {
        model.addAttribute("DTOEmployee", new DTOEmployee());
        return "addEmployees";
    }

    @Autowired
    AppointmentsService appointmentsService;

    @GetMapping("/admin/appointments")
    public String bookings(Model model) {
        List<Appointments> orders = appointmentsService.viewAllBookings();
        model.addAttribute("orders", orders);
        return "adminViewAppointments";
    }

    @GetMapping("/admin/registerEmployee")
    public String registerEmployee(Model model) {
        return "employeeRegistration";
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/appoImages";

    @PostMapping("/admin/registerEmployee")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(3).get());
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/employee/add")
    public String driverAdd(@ModelAttribute("DTOEmployee") DTOEmployee DTOEmployee) throws IOException {
        Employee employee = new Employee();
        employee.setId(DTOEmployee.getId());
        employee.setEmployee_id(DTOEmployee.getEmployee_id());
        employee.setBranch(DTOEmployee.getBranch());
        employee.setJob_id(DTOEmployee.getJob_id());
        employee.setJob_type(DTOEmployee.getJob_type());
        employee.setAddress(DTOEmployee.getAddress());
        employee.setMobileNumber(DTOEmployee.getMobileNumber());
        employeeService.addEmployee(employee);
        return "redirect:/admin/manageEmployees";
    }

    @GetMapping("/admin/employee/delete/{id}")
    public String getDeleteDriver(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/admin/manageEmployees";
    }

    @GetMapping("/admin/appointments/delete/{id}")
    public String getDeleteAppointments(@PathVariable int id) {
        appointmentsService.deleteBookings(id);
        return "redirect:/admin/appointments";
    }

    @GetMapping("/admin/employee/update/{id}")
    public String getUpdateDriver(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployeeById(id).get();
        DTOEmployee DTOEmployee = new DTOEmployee();
        DTOEmployee.setId(employee.getId());
        DTOEmployee.setEmployee_id(employee.getEmployee_id());
        DTOEmployee.setBranch(employee.getBranch());
        DTOEmployee.setJob_id(employee.getJob_id());
        DTOEmployee.setJob_type(employee.getJob_type());
        DTOEmployee.setAddress(employee.getAddress());
        DTOEmployee.setMobileNumber(employee.getMobileNumber());
        DTOEmployee.setImageName(employee.getImageName());

        model.addAttribute("DTOEmployee", DTOEmployee);

        return "updateEmployee";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "adminPage";
    }

}
