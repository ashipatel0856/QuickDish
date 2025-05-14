package com.ashish.QuickDish.config;

import org.springframework.stereotype.Service;

import java.util.Random;

public class OtpGenerator {
    public static String generateOtp() {

        Random randomOtp = new Random();
        int otp = 100000 + randomOtp.nextInt(900000); // range:1000000 to 9999999
        return String.valueOf(otp);
    }
}
