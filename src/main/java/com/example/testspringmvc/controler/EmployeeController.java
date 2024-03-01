package com.example.testspringmvc.controler;

import com.example.testspringmvc.dao.EmployeeDto;
import com.example.testspringmvc.entity.Employee;
import com.example.testspringmvc.servis.EmployeeServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmployeeController {
    private final EmployeeServise employeeServise;


    @GetMapping("/all")
    public String getAll(Model model) {
        List<EmployeeDto> employeeDtoList = employeeServise.getAll();
        model.addAttribute("employees", employeeDtoList);
        return "all";
    }
    //works just with @RestController
//    @GetMapping("/all")
//    public List<EmployeeDto> getAll() {
//        List<EmployeeDto> employeeDtoList = employeeServise.getAll();
//
//        return employeeDtoList;
//    }


    @GetMapping("/addEmp")
    public String addNewEmp(Model model){
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee",employee);

        return "info";

    }
    @PostMapping("/save")
    public String save(@ModelAttribute("employee") EmployeeDto employee){
        employeeServise.saveEmployee(employee);
        return "redirect:/all";
    }
    @GetMapping("/updateEmployee/{id}")
    public String updateEmpl(Model model, @PathVariable("id") Integer id){
        EmployeeDto dto = employeeServise.getEmployee(id);
        model.addAttribute("employee",dto);
        return "info";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmpl(@PathVariable("id") Integer id){
        employeeServise.delete(id);

        return "redirect:/all";
        }

}
