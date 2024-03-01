package com.example.testspringmvc.servis;

import com.example.testspringmvc.repository.EmployeeRepository;
import com.example.testspringmvc.converter.EmployeeConverter;
import com.example.testspringmvc.dao.EmployeeDto;
import com.example.testspringmvc.entity.Employee;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServise {
    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    @Transactional
    public List<EmployeeDto> getAll() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employeeConverter.fromModel(employees);
    }

    public void saveEmployee(EmployeeDto employee) {

        if (employee.getId() == 0) {
            Employee emp = employeeConverter.toModel(employee);
            employeeRepository.save(emp);
        }
        Employee empl = employeeRepository.findById(employee.getId()).orElseThrow();
        Employee newEmp = employeeConverter.toModel(empl, employee);
        employeeRepository.save(newEmp);
    }

    public EmployeeDto getEmployee(int id) {
        EmployeeDto dto = employeeConverter.fromModel(employeeRepository.findById(id).orElseThrow());
        return dto;
    }

    public void delete(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);

    }

}
