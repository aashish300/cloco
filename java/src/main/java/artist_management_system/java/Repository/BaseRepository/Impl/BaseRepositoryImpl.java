package artist_management_system.java.Repository.BaseRepository.Impl;

//allowing operations to either succeed completely or roll back in case of an error

import artist_management_system.java.Exception.NotAcceptableException;
import artist_management_system.java.Model.BaseEntity.BaseEntity;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.BaseRepository.IBaseRepository;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T extends BaseEntity> implements IBaseRepository<T> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BaseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//
//    @Override
//    public <S extends T> S save(S entity) {
//        String tableName = classType.getSimpleName();
//        Field[] fields = entity.getClass().getDeclaredFields();
//        StringBuilder headers = new StringBuilder();
//        StringBuilder data = new StringBuilder();
//        List<Object> params = new ArrayList<>();
//
//        for (Field field : fields) {
//            field.setAccessible(true);
//            if (field.isAnnotationPresent(Id.class) || Modifier.isTransient(field.getModifiers())) {
//                continue;
//            }
//            headers.append(field.getName()).append(",");
//            data.append("?,");
//            try {
//                params.add(field.get(entity));
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException("Error field value for " + field.getName(), e);
//            }
//        }
//
//        if(!headers.isEmpty())  {
//            headers.setLength(headers.length() -1);
//        }
//        if (!data.isEmpty())  {
//            data.setLength(data.length() -1);
//        }
//
//        String rawQuery = "INSERT INTO " + tableName + " (" + headers + ") VALUES (" + data + ")";
//
//        int result = jdbcTemplate.update(rawQuery, params.toArray());
//
//        if(result > 0) {
//            return entity;
//        }else {
//            throw new NotAcceptableException("Couldn't save data!!!");
//        }
//    }

//    @Override
//    public <S extends T> S update(String rawQuery, S entity) {
//        Field[] fields = entity.getClass().getDeclaredFields();
//        StringBuilder values = new StringBuilder();
//        List<Object> params = new ArrayList<>();
//
//        for (Field filed : fields) {
//            filed.setAccessible(true);
//            if(filed.isAnnotationPresent(Id.class) || Modifier.isTransient(filed.getModifiers())) {
//                continue;
//            }
//            values.append(filed.getName()).append(" = ").append("?,");
//            try {
//                params.add(filed.get(entity));
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException("Error field value for " + filed.getName(), e);
//            }
//        }
//        if(!values.isEmpty()) {
//            values.setLength(values.length() - 1);
//        }
//
//        String rawQuery = "UPDATE " + tableName + " SET " + values + " WHERE id = " + entity.getId();
//        int result = jdbcTemplate.update(rawQuery, params.toArray());
//
//        if(result > 0) {
//            return entity;
//        }else {
//            throw new NotAcceptableException("Couldn't save data!!!");
//        }
//    }

//    @Override
//    public T findById(ID id) {
//        return null;
//    }
//
//    @Override
//    public Page<T> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public boolean deleteById(ID id) {
//        String tableName = classType.getSimpleName();
//        String rawQuery = "DELETE FROM " + tableName + " WHERE id = ?";
//
//        int result = jdbcTemplate.update(rawQuery, id);
//        return result > 0;
//    }

    @Override
    public List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, args, rowMapper);
    }

    @Override
    public int update(String sql, Object[] args) {
        return jdbcTemplate.update(sql, args);
    }
}
