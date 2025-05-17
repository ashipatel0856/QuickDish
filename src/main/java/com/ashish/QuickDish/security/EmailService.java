package com.ashish.QuickDish.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    public  void sendOtpEmail(String toEmail , String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("QuickDish email verification otp:");
        message.setText("OTP verification sent to your email :"+otp);
        mailSender.send(message); // yha se message send hota h


//        System.out.println("OTP verification email has been sent to "+toEmail); // ye console me message display krta h


    }
}
