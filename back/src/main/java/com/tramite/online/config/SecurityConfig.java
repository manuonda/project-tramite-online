package com.tramite.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.tramite.online.config.security.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomOAuth2UserService customOAuth2UserService;

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
        .oauth2Login(oauth2 ->
           oauth2.userInfoEndpoint(userInfo ->
           userInfo.userService(customOAuth2UserService))
          )
        .logout( l -> l.logoutSuccessUrl("/login"));
        

        return http.build();
    }

}
