//package com.ashish.QuickDish.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/customer/**").authenticated()
//                        .requestMatchers("/restaurant/**").authenticated()
//                                .anyRequest().permitAll())
//
//                .csrf(csrfConfig ->
//                        csrfConfig
//                                .disable())
//
//                .sessionManagement(sessionConfig ->
//                        sessionConfig
//                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//                .cors(corsConfig ->
//                        corsConfig.disable());
//
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler(HandlerExceptionResolver handlerExceptionResolver) {
//        return (request, response, accessDeniedException) -> {
//            handlerExceptionResolver.resolveException(request,response,null,accessDeniedException);
//        };
//    }
//}
