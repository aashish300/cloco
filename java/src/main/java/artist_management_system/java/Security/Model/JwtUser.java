package artist_management_system.java.Security.Model;

import java.util.List;

public class JwtUser {

    private static String userName;
    private static int id;
    private static List<String> roles;

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        JwtUser.roles = roles;
    }
}
