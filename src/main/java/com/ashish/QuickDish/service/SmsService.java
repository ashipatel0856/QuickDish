//package com.ashish.QuickDish.service;
//
//import com.ashish.QuickDish.Entity.TwilioConfig;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SmsService {
//
//   private final TwilioConfig twilioConfig;
//
//    public SmsService(TwilioConfig twilioConfig) {
//        this.twilioConfig = twilioConfig;
//        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
//    }
//    public void sendOtpToNumbers(String phoneNumber, String otp) {
//
//
//        Message.creator(
//                new PhoneNumber(phoneNumber),
//                new PhoneNumber(twilioConfig.getPhoneNumber()),
//                "your Otp is :" + otp
//                ).create();
//
//    }
//
//
//}
