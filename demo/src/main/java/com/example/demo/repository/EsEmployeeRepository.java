package com.example.demo.repository;

import com.example.demo.controller.request.ApiSearchRequest;
import com.example.demo.controller.response.ApiSearchResponse;
import com.example.demo.dto.EmployeeDto;
import org.springframework.stereotype.Repository;

@Repository
public interface EsEmployeeRepository {
    boolean create(EmployeeDto employee);

    ApiSearchResponse search(ApiSearchRequest request);
}
