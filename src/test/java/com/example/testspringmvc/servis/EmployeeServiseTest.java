package com.example.testspringmvc.servis;

import com.example.testspringmvc.converter.EmployeeConverter;
import com.example.testspringmvc.dao.EmployeeDto;
import com.example.testspringmvc.entity.Employee;
import com.example.testspringmvc.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiseTest {
    private static final int EMPLOYEE_ID = 111;
    @InjectMocks
    private EmployeeServise testInstance;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeConverter employeeConverter;
    @Mock
    private Employee employee;


    private EmployeeDto dto = new EmployeeDto();
    @Mock
    private List<Employee> allEmployee;

    @Test
    void shouldReturnAllEmployee(){
        when(employeeRepository.findAll()).thenReturn(allEmployee);

        testInstance.getAll();

        verify(employeeRepository).findAll();


    }


    @Test
    void shouldReturnGetEmployeeById() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employee));

        dto.setId(EMPLOYEE_ID);
        when(employeeConverter.fromModel(employee)).thenReturn(dto);

        testInstance.getEmployee(EMPLOYEE_ID);

        verify(employeeRepository).findById(EMPLOYEE_ID);
        verify(employeeConverter).fromModel(employee);

        assertEquals(EMPLOYEE_ID, dto.getId());

    }

    @Test
    void shouldExceptionGetEmployeeById() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenThrow(RuntimeException.class);
        assertThrowsExactly(RuntimeException.class, () -> {
            testInstance.getEmployee(EMPLOYEE_ID);

        });
    }


    @Test
    void shouldDeleteEmployee() {
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employee));

        testInstance.delete(EMPLOYEE_ID);

        verify(employeeRepository).delete(employee);

    }
    //    public void saveEmployee(EmployeeDto dto) {
    //
    //        if (employee.getId() == 0) {
    //            Employee emp = employeeConverter.toModel(dto);
    //            employeeRepository.save(emp);
    //        }
    //        Employee empl = employeeRepository.findById(employee.getId()).orElseThrow();
    //        Employee newEmp = employeeConverter.toModel(empl, dto);
    //        employeeRepository.save(newEmp);
    //    }
    //    @Test
    //    void shouldReturnSaveTest() {
    //
    //        when(orderConverter.toModel(dto)).thenReturn(order);
    //
    //        testInstance.save(dto);
    //
    //        verify(orderConverter).toModel(dto);
    //        verify(orderRepository).save(order);
    //    }
    @Test
    void saveEmployeeNew(){

        when(employeeRepository.findById(0)).thenReturn(Optional.of(employee));
        when(employeeConverter.toModel(dto)).thenReturn(employee);
        testInstance.saveEmployee(dto);

        verify(employeeRepository).findById(0);
        verify(employeeConverter).toModel(dto);
    }
    @Test
    void updateEmployee(){
        when(employeeRepository.findById(EMPLOYEE_ID)).thenReturn(Optional.of(employee));
        dto.setId(EMPLOYEE_ID);
        when(employeeConverter.toModel(employee,dto)).thenReturn(any());

        testInstance.saveEmployee(dto);

        verify(employeeRepository).findById(EMPLOYEE_ID);
        verify(employeeConverter).toModel(any(),any());
    }


}