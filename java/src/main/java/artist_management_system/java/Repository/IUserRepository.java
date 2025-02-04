package artist_management_system.java.Repository;

import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.BaseRepository.IBaseRepository;

import java.util.List;

public interface IUserRepository extends IBaseRepository<UserEntity> {

    List<UserEntity> findByEmail(String email);

    boolean save(UserEntity user);

    UserEntity findById(Integer id);

    boolean update(UserEntity user);

    boolean deleteById(Integer id);

    List<UserEntity> findAllByPagination(int limit, int offset);

    List<UserEntity> findAll();
}
