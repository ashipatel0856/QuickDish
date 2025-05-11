package com.ashish.QuickDish.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
//    private String refreshToken;





    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

//
//    public String getAccessToken() {
//        return accessToken;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//
//    public String getRefreshToken() {
//        return refreshToken;
//    }
//
//    public void setRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }
}
