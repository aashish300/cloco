package artist_management_system.java.Repository.Impl;

import artist_management_system.java.Mapper.UserMapper;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.BaseRepository.Impl.BaseRepositoryImpl;
import artist_management_system.java.Repository.IUserRepository;
import artist_management_system.java.Utils.Enum.Gender;
import artist_management_system.java.Utils.Enum.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepositoryImpl<UserEntity>  implements IUserRepository {

    private UserMapper userMapper;

    public UserRepository(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        super(jdbcTemplate);
        this.userMapper = userMapper;
    }

    public boolean save(UserEntity user) {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "INSERT INTO " + tableName + " (first_name, last_name, email, password, phone, dob, gender, user_role, address," +
                "token, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = update(rawQuery, new Object[]{
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getDob(),
                user.getGender().name(),
                user.getRole().name(),
                user.getAddress(),
                user.getToken(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
        });
        return result != 0;
    }

    @Override
    public List<UserEntity> findByEmail(String email) {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "SELECT * FROM " + tableName + " WHERE email LIKE ?";

        return query(rawQuery, new Object[]{email}, userMapper.getEmailVerificationUserEntityRowMapper());
    }

    @Override
    public UserEntity findById(Integer id) {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "SELECT * FROM " + tableName + " WHERE id = ?";

        return queryForObject(rawQuery, new Object[]{id}, userMapper.getDisplayUserEntityRowMapper());
    }

    @Override
    public boolean update(UserEntity user) {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "UPDATE " + tableName + " SET first_name = ?, last_name = ?, email = ?, phone = ?, dob = ?, "
                + "gender = ?, user_role = ?, address = ? WHERE id = ?";
        int result = update(rawQuery, new Object[]{
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getDob(),
                user.getGender().name(),
                user.getRole().name(),
                user.getAddress(),
        });
        return result != 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "DELETE " + tableName + " WHERE id = ?";
        int result = update(rawQuery, new Object[]{id});

        return result != 0;
    }

    @Override
    public List<UserEntity> findAllByPagination(int limit, int offset) {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY first_name LIMIT = ? OFFSET = ?";

        return query(rawQuery, new Object[]{limit, offset}, userMapper.getDisplayUserEntityRowMapper());
    }

    @Override
    public List<UserEntity> findAll() {
        String tableName = UserEntity.class.getSimpleName();
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY first_name";

        return query(rawQuery, new Object[]{}, userMapper.getDisplayUserEntityRowMapper());
    }
}
