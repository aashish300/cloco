package artist_management_system.java.Repository;

import artist_management_system.java.Model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    List<UserEntity> findByEmail(String email);

    int save(UserEntity user);

//    Optional<UserEntity> findByEmail(String email);
}
