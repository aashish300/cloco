package artist_management_system.java.Security.Auth;

import artist_management_system.java.Security.Model.JwtAuthenticationToken;
import artist_management_system.java.Security.Model.JwtUser;
import artist_management_system.java.Security.Model.JwtUserDetails;
import artist_management_system.java.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final JwtValidator validator;

    @Autowired
    JwtAuthenticationProvider(JwtValidator validator){
        this.validator = validator;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        JwtUser jwtUser = validator.validate(token);

        if(jwtUser == null){
            throw new BadCredentialsException("Invalid token");
        }

        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils
                .commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaSeparatedString(jwtUser.getRoles()));
        return new JwtUserDetails(jwtUser.getUserName(), token, jwtUser.getId(), grantedAuthorityList);
    }

    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
