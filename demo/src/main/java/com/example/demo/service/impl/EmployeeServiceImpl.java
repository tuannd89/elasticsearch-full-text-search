package com.example.demo.service.impl;

import com.example.demo.controller.request.ApiEmployeeUpdateRequest;
import com.example.demo.controller.request.ApiSearchRequest;
import com.example.demo.controller.response.ApiEmployeeUpdateResponse;
import com.example.demo.controller.response.ApiSearchResponse;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EsEmployeeRepository;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EsEmployeeRepository esEmployeeRepository;

    @Override
    public ApiEmployeeUpdateResponse create(ApiEmployeeUpdateRequest request) {
        Employee employee = employeeRepository.save(request.toEntityObject());
        esEmployeeRepository.create(new EmployeeDto(employee.getId(), request.getEmpName()));
        return null;
    }

    @Override
    public ApiSearchResponse search(ApiSearchRequest request) {
        return  esEmployeeRepository.search(request);
    }
}
