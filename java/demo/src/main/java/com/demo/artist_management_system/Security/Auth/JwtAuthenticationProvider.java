package com.demo.artist_management_system.Security.Auth;

import com.demo.artist_management_system.Exception.NotFoundException;
import com.demo.artist_management_system.Security.Model.JwtAuthenticationToken;
import com.demo.artist_management_system.Security.Model.JwtUser;
import com.demo.artist_management_system.Security.Model.JwtUserDetails;
import com.demo.artist_management_system.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();

        JwtUser jwtUser = validator.validate(token);
        if(jwtUser == null) {
            throw new NotFoundException("JWT Token is incorrect.");
        }

        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils
                .commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaSeparatedString(jwtUser.getRole()));
        return new JwtUserDetails(jwtUser.getUserName(),
                token,
                jwtUser.getId(),
                grantedAuthorityList);
    }

    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
