package artist_management_system.java.Service;

import artist_management_system.java.Model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserEntity findByEmail(String email);

    UserEntity save(UserEntity user);

    UserEntity findById(Integer id);

    UserEntity update(UserEntity user);

    boolean deleteById(Integer id);

    List<UserEntity> findAllByPagination(Integer page, Integer size);

    List<UserEntity> findAll();
}
