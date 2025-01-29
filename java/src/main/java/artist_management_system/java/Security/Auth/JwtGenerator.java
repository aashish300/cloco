package artist_management_system.java.Security.Auth;

import artist_management_system.java.Security.Model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    @Value("${jwt-secret}")
    private String secretKey;

    private static final long JWT_EXPIRATION_TIME = 86400000;

    public String generate(JwtUser jwtUser) {
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName()).build();
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("roles", jwtUser.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_TIME))
                .compact();
    }
}
