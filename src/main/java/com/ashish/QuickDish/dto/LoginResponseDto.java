package com.ashish.QuickDish.dto;

import lombok.*;
@Data
public class LoginResponseDto {
    private String accessToken;


    public LoginResponseDto() {
    }

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

