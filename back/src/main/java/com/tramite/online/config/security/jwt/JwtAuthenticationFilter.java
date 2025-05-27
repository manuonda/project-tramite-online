package com.tramite.online.config.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tramite.online.service.CustomUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final TokenProvider tokenProvider;
    private final CustomUserDetailService customUserDetailService;

    public JwtAuthenticationFilter(TokenProvider tokenProvider, CustomUserDetailService customUserDetailService) {
        this.tokenProvider = tokenProvider;
        this.customUserDetailService = customUserDetailService;
    }

  // Rutas que NO deben pasar por JWT filter
       private static final List<String> EXCLUDED_PATHS = Arrays.asList(
        "/",
        "/error",
        "/login",
        "/oauth2/authorization",    // Inicio OAuth2
        "/oauth2/callback",         // Si usas callback personalizado
        "/login/oauth2/code"        // Callbacks estándar
    );
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return EXCLUDED_PATHS.stream()
        .anyMatch(excluded -> path.startsWith(excluded));
    }



    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        logger.info("doFilterInternal");
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            logger.info("AuthHeader is null  || auth Header no content Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = this.tokenProvider.getJWTFromRequest(request);
        String userName = this.tokenProvider.getUsernameFromToken(jwt);
        try {

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
                if (Boolean.TRUE.equals(this.tokenProvider.validateToken(jwt, userDetails))) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    logger.debug("JWT Authentication success for username {}", userName);
                }

            }
        } catch (UsernameNotFoundException e) {
            logger.error("User Not Found for username/email from JWT : ", e);
        } catch (ExpiredJwtException e) {
            logger.warn("Expired JWT Token : {} - {}", e.getMessage(), request.getRequestURI());
        } catch (JwtException e) {
            logger.warn("Invalid JWT Token : {} - {}", e.getMessage(), request.getRequestURI());
        } catch (Exception e) {
            logger.error("Could not set user authentication in security context", e);
        }

        filterChain.doFilter(request, response);

    }

}
