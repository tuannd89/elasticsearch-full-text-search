package com.example.demo.controller;

import com.example.demo.controller.request.ApiEmployeeUpdateRequest;
import com.example.demo.controller.request.ApiSearchRequest;
import com.example.demo.controller.response.ApiEmployeeUpdateResponse;
import com.example.demo.controller.response.ApiSearchResponse;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public ApiEmployeeUpdateResponse create(@RequestBody ApiEmployeeUpdateRequest request) {
        return employeeService.create(request);
    }

    @GetMapping("/search")
    public ApiSearchResponse search(@RequestParam("keyword") String keyword) {
        return employeeService.search(ApiSearchRequest.of(keyword));
    }
}
