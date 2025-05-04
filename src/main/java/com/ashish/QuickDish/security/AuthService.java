package com.ashish.QuickDish.security;
import com.ashish.QuickDish.Entity.User;
import com.ashish.QuickDish.dto.LoginDto;
import com.ashish.QuickDish.dto.SignUpRequestDto;
import com.ashish.QuickDish.dto.UserDto;
import com.ashish.QuickDish.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    // user login signup and refresh
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UserDto SignUp (SignUpRequestDto signUpRequestDto) {
        User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeException("Email already in use");
        }
        User newUser = modelMapper.map(signUpRequestDto, User.class);
        newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        newUser = userRepository.save(newUser);
        return modelMapper.map(newUser, UserDto.class);
    }


    public String[] login(LoginDto loginDto){
        Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),loginDto.getPassword()));

        User user = (User)authentication.getPrincipal();
        String[] arr = new String[2];
        arr[0] = jwtService.generateAccessToken(user);
        arr[1] = jwtService.generateRefreshToken(user);
        return arr;

    }
    public String refreshToken(String token) {
        Long id = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(id).orElse(null);
        return jwtService.generateRefreshToken(user);
    }
}
