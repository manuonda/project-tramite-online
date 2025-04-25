package com.tramite.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.tramite.online.config.security.oauth2.service.CustomOAuth2UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSucessHandler;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSucessHandler) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2AuthenticationSucessHandler = oAuth2AuthenticationSucessHandler;
    }

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
            
        })
        //.httpBasic(Customizer.withDefaults())
        //.formLogin(Customizer.withDefaults())
        .oauth2Login(oauth2 ->
           oauth2.userInfoEndpoint(userInfo ->
           userInfo.userService(customOAuth2UserService)
           ).successHandler(oAuth2AuthenticationSucessHandler)
          )
        .logout( l -> l.logoutSuccessUrl("/login"));
        

        return http.build();
    }

}
