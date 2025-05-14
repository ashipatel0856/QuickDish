package com.ashish.QuickDish.security;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public  void sendOtpEmail(String to , String otp) {
        System.out.println("Otp for"+to+ ":"+ otp);
    }
}
