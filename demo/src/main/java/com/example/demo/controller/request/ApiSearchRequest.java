package com.example.demo.controller.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiSearchRequest {
    private String keyword;

    public static ApiSearchRequest of(String keyword) {
        return ApiSearchRequest.builder()
                            .keyword(keyword)
                            .build();
    }

}
