package com.demo.artist_management_system.Security.Model;

import java.util.List;

public class JwtUser {

    private static String userName;
    private static int id;
    private static List<String> role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        JwtUser.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        JwtUser.id = id;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        JwtUser.role = role;
    }
}
