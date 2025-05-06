package com.ashish.QuickDish.advice;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse( T data) {
        this();
        this.data = data;

    }
    public ApiResponse( ApiError error) {
        this();
        this.error = error;
    }
}
