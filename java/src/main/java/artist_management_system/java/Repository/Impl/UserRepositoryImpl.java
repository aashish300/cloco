package artist_management_system.java.Repository.Impl;

import artist_management_system.java.Mapper.UserMapper;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.BaseRepository.Impl.BaseRepositoryImpl;
import artist_management_system.java.Repository.IUserRepository;
import jakarta.persistence.Table;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<UserEntity>  implements IUserRepository {

    private UserMapper userMapper;
    private final String tableName = UserEntity.class.getAnnotation(Table.class).name();

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        super(jdbcTemplate);
        this.userMapper = userMapper;
    }

    public boolean save(UserEntity user) {
        String rawQuery = "INSERT INTO " + tableName + " (first_name, last_name, email, password, phone, dob, gender, user_role, address," +
                "token, created_at) VALUES (?, ?, ?, ?, ?, ?, ?::gender_enum, ?::user_role_enum, ?, ?, ?)";
        int result = update(rawQuery, new Object[]{
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                user.getDob(),
                user.getGender() != null ? user.getGender().name() : null,
                user.getRole() != null ? user.getRole().name() : null,
                user.getAddress(),
                user.getToken(),
                user.getCreatedAt(),
        });
        return result != 0;
    }

    @Override
    public List<UserEntity> findByEmail(String email) {
        String rawQuery = "SELECT * FROM " + tableName + " WHERE email LIKE ?";

        return query(rawQuery, new Object[]{email}, userMapper.getEmailVerificationUserEntityRowMapper());
    }

    @Override
    public UserEntity findById(Integer id) {
        String rawQuery = "SELECT * FROM " + tableName + " WHERE id = ?";

        return queryForObject(rawQuery, new Object[]{id}, userMapper.getDisplayUserEntityRowMapper());
    }

    @Override
    public boolean update(UserEntity user) {
        String rawQuery = "UPDATE " + tableName + " SET first_name = ?, last_name = ?, email = ?, phone = ?, dob = ?, "
                + "gender = ?::gender_enum, user_role = ?::user_role_enum, address = ?, updated_at = ? WHERE id = ?";
        int result = update(rawQuery, new Object[]{
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone(),
                user.getDob(),
                user.getGender() != null ? user.getGender().name() : null,
                user.getRole() != null ? user.getRole().name() : null,
                user.getAddress(),
                user.getUpdatedAt()
        });
        return result != 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        String rawQuery = "DELETE " + tableName + " WHERE id = ?";
        int result = update(rawQuery, new Object[]{id});

        return result != 0;
    }

    @Override
    public List<UserEntity> findAllByPagination(int limit, int offset) {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY first_name LIMIT = ? OFFSET = ?";

        return query(rawQuery, new Object[]{limit, offset}, userMapper.getDisplayUserEntityRowMapper());
    }

    @Override
    public List<UserEntity> findAll() {
        String rawQuery = "SELECT * FROM " + tableName + " ORDER BY first_name";

        return query(rawQuery, new Object[]{}, userMapper.getDisplayUserEntityRowMapper());
    }
}
