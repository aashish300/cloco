package artist_management_system.java.Security.Auth;

import artist_management_system.java.Security.Model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtValidator {
    @Value("${jwt-secret}")
    private String secretKey;

    public JwtUser validate(String token) {
        JwtUser jwtUser = new JwtUser();

        try {
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Integer.parseInt((String) body.get("userId")));
            jwtUser.setRoles((List<String>) body.get("roles"));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return jwtUser;
    }
}
