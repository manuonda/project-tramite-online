package com.tramite.online.config.security.jwt;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tramite.online.config.ApplicationProperties;
import com.tramite.online.config.security.principal.UserPrincipal;
import com.tramite.online.domain.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;




/**
 * This class is responsible for generating tokens and validating the
 * information contained within them.
 */
@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private final ApplicationProperties appProperties;

    public TokenProvider(ApplicationProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String generateAccessToken(User user) {
        logger.info("Generate Access Token : {}", user);
        return Jwts.builder().subject(user.getUserName())
                // .claim("roles", user.getRole().name().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.appProperties.jwtExpiration()))
                .signWith(getSigninKey()).compact();
    }

    public String generateRefreshToken(User user) {
        logger.info("Generate Refresh Token : {}", user);
        return Jwts.builder().subject(user.getUserName()).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.appProperties.jwtRefreshToken()))
                .signWith(getSigninKey()).compact();
    }

    public boolean isValidAccessToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    public boolean isValidRefreshToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername)) && !isTokenExpired(token);
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(this.appProperties.jwtSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }

        return null;
    }

     public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = this.getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

     public String createToken(Authentication authentication) {
        logger.info("Create Token for user ");
        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        
    
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + appProperties.jwtExpiration());
        return Jwts.builder()
          .subject(userPrincipal.getId().toString())
          .claim("username", userPrincipal.getUsername())
          .issuedAt(now)
          .expiration(expirationDate)
          .signWith(getSigninKey())
          .compact();
    

     }

  

}
