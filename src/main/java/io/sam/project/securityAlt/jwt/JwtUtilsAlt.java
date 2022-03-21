package io.sam.project.securityAlt.jwt;

import io.jsonwebtoken.*;
import io.sam.project.security.services.UserDetailsImpl;
import io.sam.project.securityAlt.services.UserDetailsImplAlt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

import java.util.Date;

@Component
@Slf4j
public class JwtUtilsAlt {
    @Value("${sam.app.jwtSecret}")
    private String jwtSecret;

    @Value("${sam.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${sam.app.jwtCookieName}")
    private String jwtCookie;

    public String generateTokenFromUsername(UserDetailsImpl user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }


    public boolean validateJwtToken(String authToken) {
        System.out.println(authToken);
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
