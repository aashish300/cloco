package com.demo.artist_management_system.Repository.Impl;

import com.demo.artist_management_system.Model.UserEntity;
import com.demo.artist_management_system.Repository.IUserRepository;
import com.demo.artist_management_system.Utils.Enum.Role;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    private Class<UserEntity> userEntityClass;

    public UserRepository(JdbcTemplate jdbcTemplate, Class<UserEntity> userEntityClass) {
        this.jdbcTemplate = jdbcTemplate;
        this.userEntityClass = userEntityClass;
    }

    @Override
    public UserEntity findByEmail(String email) {
        String tableName = userEntityClass.getSimpleName();
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
        } catch (EmptyResultDataAccessException e) {
            return null;  // No user found with the given email
        }
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return null;
    }
}
