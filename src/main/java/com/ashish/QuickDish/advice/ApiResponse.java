package com.ashish.QuickDish.advice;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
