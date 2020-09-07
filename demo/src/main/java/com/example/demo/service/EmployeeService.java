package com.example.demo.service;

import com.example.demo.controller.request.ApiEmployeeUpdateRequest;
import com.example.demo.controller.request.ApiSearchRequest;
import com.example.demo.controller.response.ApiEmployeeUpdateResponse;
import com.example.demo.controller.response.ApiSearchResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    ApiEmployeeUpdateResponse create(ApiEmployeeUpdateRequest request);

    ApiSearchResponse search(ApiSearchRequest request);
}
