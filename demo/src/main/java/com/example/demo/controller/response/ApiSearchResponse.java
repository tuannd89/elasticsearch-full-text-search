package com.example.demo.controller.response;

import com.example.demo.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiSearchResponse {
    private long total;
    private List<EmployeeDto> items;

    public static ApiSearchResponse of(long total, List<EmployeeDto> items) {
        return ApiSearchResponse.builder()
                                .total(total)
                                .items(items)
                                .build();
    }
}
