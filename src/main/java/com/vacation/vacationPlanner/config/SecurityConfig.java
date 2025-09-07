package com.vacation.vacationPlanner.config;import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // BCrypt bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ new way to disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow everything for now
                )
                .formLogin(form -> form.disable())   // disable login page
                .httpBasic(basic -> basic.disable()); // disable basic auth

        return http.build();
    }
}
