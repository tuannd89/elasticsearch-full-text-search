package com.example.demo.controller.request;

import com.example.demo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiEmployeeUpdateRequest {
    private String empName;

    public Employee toEntityObject() {
        Employee employee = new Employee();
        employee.setEmpName(this.empName);
        return employee;
    }
}
