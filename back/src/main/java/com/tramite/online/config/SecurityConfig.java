package com.tramite.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .cors(cors -> cors.disable())
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> {
             authorize
            .requestMatchers("/api/v1/**").permitAll()
            .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
            .anyRequest().authenticated();
            
        }).httpBasic(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .logout( l -> l.logoutSuccessUrl("/login"));
        

        return http.build();
    }
}
