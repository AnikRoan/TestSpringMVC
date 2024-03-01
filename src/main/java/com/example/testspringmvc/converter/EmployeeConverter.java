package com.example.testspringmvc.converter;

import com.example.testspringmvc.dao.EmployeeDto;
import com.example.testspringmvc.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeConverter {
    public EmployeeDto fromModel(Employee employee){
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .department(employee.getDepartment())
                .salary(employee.getSalary())
                .build();
    }


    public List<EmployeeDto> fromModel(Iterable<Employee> employees){
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee e:employees){
            employeeDtos.add(fromModel(e));
        }
        return employeeDtos;
    }

    public Employee toModel(EmployeeDto dto){
        return Employee.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .department(dto.getDepartment())
                .salary(dto.getSalary())
                .build();
    }
    public Employee toModel(Employee employee, EmployeeDto dto) {
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());
        return employee;
    }
}
