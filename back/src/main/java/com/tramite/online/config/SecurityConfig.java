package com.tramite.online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tramite.online.config.security.jwt.JwtAuthenticationFilter;
import com.tramite.online.config.security.oauth2.CustomAuthorizationRequestResolver;
import com.tramite.online.config.security.oauth2.handler.OAuth2AuthenticationFailureHandler;
import com.tramite.online.config.security.oauth2.handler.OAuth2AuthenticationSuccessHandler;
import com.tramite.online.config.security.oauth2.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.tramite.online.config.security.oauth2.service.CustomOAuth2UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final static String OAUTH2_BASE_URI = "/oauth2/authorize";
    private final static String OAUTH2_REDIRECTION_ENDPOINT = "/oauth2/callback/*";


    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSucessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ClientRegistrationRepository clientRegistrationRepository;



    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, 
        OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSucessHandler,
        OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler,
        JwtAuthenticationFilter jwtAuthenticationFilter ,
        HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository, ClientRegistrationRepository clientRegistrationRepository) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2AuthenticationSucessHandler = oAuth2AuthenticationSucessHandler;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.httpCookieOAuth2AuthorizationRequestRepository= httpCookieOAuth2AuthorizationRequestRepository;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
        this.clientRegistrationRepository = clientRegistrationRepository;
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
        .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(authorizationEndpointConfig -> authorizationEndpointConfig
                                .baseUri(OAUTH2_BASE_URI)
                                .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                                .authorizationRequestResolver(new CustomAuthorizationRequestResolver(clientRegistrationRepository, OAUTH2_BASE_URI))
                        )
                        .redirectionEndpoint(redirectionEndpointConfig -> redirectionEndpointConfig.baseUri(OAUTH2_REDIRECTION_ENDPOINT))
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2UserService))
                        .tokenEndpoint(tokenEndpointConfig -> tokenEndpointConfig.accessTokenResponseClient(authorizationCodeTokenResponseClient()))
                        .successHandler(oAuth2AuthenticationSucessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler)
                );

        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}
