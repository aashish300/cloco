package artist_management_system.java.Repository.Impl;

import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.BaseRepository.Impl.BaseRepositoryImpl;
import artist_management_system.java.Repository.IUserRepository;
import artist_management_system.java.Utils.Enum.Role;
import jakarta.persistence.PersistenceContext;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends BaseRepositoryImpl<UserEntity>  implements IUserRepository {

    public UserRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public int save(UserEntity user) {
        String sql = "insert into tbl_user (first_name, last_name, email, password, phone, dob, gender, user_role, address," +
                "token, created_at, updated_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return update(sql, new Object[]{
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getDob(),
                user.getGender(),
                user.getRole().name(),
                user.getAddress(),
                user.getToken(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
        });
    }
    @Override
    public List<UserEntity> findByEmail(String email) {
        String tableName = "tbl_user";
        String rawQuery = "SELECT * FROM " + tableName + " WHERE email LIKE ?";

        return query(rawQuery, new Object[]{email}, (rs, rowNum) -> {
            UserEntity user = new UserEntity();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("user_role")));
            return user;
        });
    }
}
