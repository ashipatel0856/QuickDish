package com.ashish.QuickDish.config;

import org.springframework.stereotype.Service;

import java.util.Random;

public class OtpGenerator {
    public static String generateOtp() {

        Random randomOtp = new Random();
        int otp = 1000000 + randomOtp.nextInt(9000000);
        return String.valueOf(otp);
    }
}
