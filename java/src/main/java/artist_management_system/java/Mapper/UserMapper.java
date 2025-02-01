package artist_management_system.java.Mapper;

import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Utils.Enum.Gender;
import artist_management_system.java.Utils.Enum.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public RowMapper<UserEntity> getDisplayUserEntityRowMapper() {
        return (rs, rowNum) -> {
            UserEntity user = new UserEntity();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setDob(rs.getDate("dob"));
            user.setAddress(rs.getString("address"));
            user.setGender(Gender.valueOf(rs.getString("gender")));
            user.setRole(Role.valueOf(rs.getString("user_role")));
            user.setPhone(rs.getString("phone"));
            return user;
        };
    }

    public RowMapper<UserEntity> getEmailVerificationUserEntityRowMapper() {
        return (rs, rowNum) -> {
            UserEntity user = new UserEntity();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("user_role")));
            return user;
        };
    }
}
