//package com.ashish.QuickDish.security;
//
//import com.ashish.QuickDish.Entity.User;
//import com.ashish.QuickDish.dto.LoginDto;
//import com.ashish.QuickDish.dto.LoginResponseDto;
//import com.ashish.QuickDish.dto.SignupDto;
//import com.ashish.QuickDish.dto.UserDto;
//import com.ashish.QuickDish.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final ModelMapper modelMapper;
//    private final JWTService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, JWTService jwtService, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.modelMapper = modelMapper;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    public UserDto signUp(SignupDto signupDto){
//        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
//        if(user != null){
//            throw new RuntimeException("user is already regiqested with the same email");
//        }
//
//
//        User newUser = modelMapper.map(signupDto, User.class);
//        newUser.setPassword(passwordEncoder.encode(signupDto.getPassword()));
//        newUser = userRepository.save(newUser);
//        return modelMapper.map(newUser, UserDto.class);
//    }
//
//    public String[] login(LoginDto loginDto){
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
//        );
//        User user = userRepository.findByEmail(loginDto.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        String[] arr = new String[2];
//       arr[0] = jwtService.generateAccessToken(user);
//        arr[1] = jwtService.generateRefreshToken(user);
//
//        return arr;
//
//    }
////
////    public String refreshToken(String refreshToken){
////        Long id = jwtService.getUserIdFromToken(refreshToken);
////        User user = userRepository.findById(id).orElse(null);
////        return jwtService.generateRefreshToken(user);
////    }
//
//}
