package com.example.infomansys.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // public endpoints
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/registration").permitAll()

                // admin only
                .requestMatchers(HttpMethod.GET, "/api/members").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/members").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/contacts/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/contacts/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/members/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/contacts/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/dependents/**").hasRole("ADMIN")

                // any authenticated user (controller methods will enforce pinId ownership)
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}