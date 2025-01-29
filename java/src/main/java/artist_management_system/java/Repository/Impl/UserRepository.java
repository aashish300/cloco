package artist_management_system.java.Repository.Impl;

import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.BaseRepository.Impl.BaseRepositoryImpl;
import artist_management_system.java.Repository.IUserRepository;
import artist_management_system.java.Utils.Enum.Role;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepositoryImpl<UserEntity, Integer>  implements IUserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, UserEntity.class);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserEntity findByEmail(String email) {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "SELECT * FROM " + tableName + " WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(rawQuery, new Object[]{email}, (rs, rowNum) -> {
                UserEntity user = new UserEntity();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                return user;
            });
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserEntity save(UserEntity user) {
        return null;
    }
}
