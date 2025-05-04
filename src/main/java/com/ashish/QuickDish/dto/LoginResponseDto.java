package com.ashish.QuickDish.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class LoginResponseDto {
    private String accessToken;

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
