package artist_management_system.java.Repository.BaseRepository.Impl;

//allowing operations to either succeed completely or roll back in case of an error

import artist_management_system.java.Model.BaseEntity.BaseEntity;
import artist_management_system.java.Repository.BaseRepository.IBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class BaseRepositoryImpl<T extends BaseEntity> implements IBaseRepository<T> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BaseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, args, rowMapper);
    }

    @Override
    public int update(String sql, Object[] args) {
        return jdbcTemplate.update(sql, args);
    }


    @Override
    public T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, args, rowMapper);
    }
}
