package com.ashish.QuickDish.security;

import com.ashish.QuickDish.Entity.Otp;
import com.ashish.QuickDish.Entity.User;
import com.ashish.QuickDish.Entity.enums.Role;
import com.ashish.QuickDish.advice.ApiResponse;
import com.ashish.QuickDish.config.OtpGenerator;
import com.ashish.QuickDish.dto.*;
import com.ashish.QuickDish.repository.OtpRepository;
import com.ashish.QuickDish.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class AuthService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final ModelMapper modelMapper;
        private final AuthenticationManager authenticationManager;
        private final JWTService jwtService;
        private final OtpRepository otpRepository;
        private final EmailService emailService;


        public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, AuthenticationManager authenticationManager, JWTService jwtService, OtpRepository otpRepository, EmailService emailService) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.modelMapper = modelMapper;
            this.authenticationManager = authenticationManager;
            this.jwtService = jwtService;
            this.otpRepository = otpRepository;
            this.emailService = emailService;
        }

        public ApiResponse<String> signUp(SignupDto signUpRequestDto) {

            User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);
            if (user != null) {
                throw new RuntimeException("User is already registered with same email");
            }

            User newUser = modelMapper.map(signUpRequestDto, User.class);
            newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
            newUser.setVerified(false);  // not verified


            try {
                Role role = Role.valueOf(signUpRequestDto.getRole().toUpperCase());
                newUser.setRole(Set.of(role));

            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid role based: " + signUpRequestDto.getRole());
            }


            userRepository.save(newUser);

            // generate otp and save

            String otpGenerate = OtpGenerator.generateOtp();
            Otp otp = new Otp();
            otp.setEmail(signUpRequestDto.getEmail());
            otp.setOtp(otpGenerate);
            otp.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
            otpRepository.save(otp);

            // SEND OTP TO YOUR EMAIL
            emailService.sendOtpEmail(signUpRequestDto.getEmail(),otpGenerate);
//            return modelMapper.map(newUser, UserDto.class);

            // send otp to mobile numbers
//            smsService.sendOtpToNumbers(signUpRequestDto.getPhone(),otpGenerate);
            return new ApiResponse<>(" signUp succcessful . Otp sent to your email for verifications :");
        }


    public String[] login(LoginDto loginDto) {
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(), loginDto.getPassword()));

        User user=(User)authentication.getPrincipal();
        String[] arr =new String[2];
        arr[0]=jwtService.generateAccessToken(user);
        arr[1]=jwtService.generateRefreshToken(user);

        return arr;

    }



    public String refreshToken(String refreshToken) {
            Long id = jwtService.getUserIdFromToken(refreshToken);

            User user = userRepository.findById(id).orElse(null);
            return jwtService.generateRefreshToken(user);
        }

    public ApiResponse<String> VerifyOtp(OtpRequestDto otpRequestDto) {

            // checking otp record
         Otp otp = otpRepository.findByEmail(otpRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("OTP not found for that email"));

         // checking opt expired or not
        if(otp.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            throw  new RuntimeException("your Otp has been expired and  try again");
        }

        // otp matching checking
        if(!otp.getOtp().equals(otpRequestDto.getOtp())) {
            throw new RuntimeException("OTP does not match");
        }

        // user verifications

        User user = userRepository.findByEmail(otp.getEmail()).orElseThrow(
                () -> new RuntimeException("User not found for that email"));

        user.setVerified(true);
        userRepository.save(user);

        // DELETE OTP
        otpRepository.delete(otp);
        return  new ApiResponse<>("OTP verified successfully and you can now login in :");
    }

    }
