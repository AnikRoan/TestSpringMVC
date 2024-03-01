package com.example.testspringmvc.dao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDto {
    private  int id;
    private String name;
    private String surname;
    private String department;
    private  int salary;
}
