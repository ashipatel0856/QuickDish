//package com.ashish.QuickDish.controller;
//
//import com.ashish.QuickDish.dto.LoginDto;
//import com.ashish.QuickDish.dto.LoginResponseDto;
//import com.ashish.QuickDish.dto.SignupDto;
//import com.ashish.QuickDish.dto.UserDto;
//import com.ashish.QuickDish.security.AuthService;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping("/sign")
//    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
//        return new ResponseEntity<>(authService.signUp(signupDto), HttpStatus.CREATED);
//    }
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse) {
//        String[] tokens = authService.login(loginDto);
//
//        // jwt token pass in cookie;
//        Cookie cookie = new Cookie("refreshToken", tokens[1]);
//        cookie.setHttpOnly(true);
//
//        httpServletResponse.addCookie(cookie);
//
//        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
//    }
//}
