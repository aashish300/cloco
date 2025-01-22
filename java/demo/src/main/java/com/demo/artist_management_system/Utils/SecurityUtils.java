package com.demo.artist_management_system.Utils;

import com.demo.artist_management_system.Security.Model.JwtUserDetails;
import com.demo.artist_management_system.Utils.Enum.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static JwtUserDetails getUserCredentials() {
        return (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static boolean isSuperAdmin() {
        for(GrantedAuthority authority : SecurityUtils.getUserCredentials().getAuthorities()) {
            if(authority.getAuthority().equals(Role.SUPER_ADMIN.toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isArtistManager() {
        for(GrantedAuthority authority : SecurityUtils.getUserCredentials().getAuthorities()) {
            if(authority.getAuthority().equals(Role.ARTIST_MANAGER.toString())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isArtist() {
        for(GrantedAuthority authority : SecurityUtils.getUserCredentials().getAuthorities()) {
            if(authority.getAuthority().equals(Role.ARTIST.toString())) {
                return true;
            }
        }
        return false;
    }
}
