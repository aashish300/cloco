package artist_management_system.java.Repository;

import artist_management_system.java.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository {

    UserEntity findByEmail(String email);

    UserEntity save(UserEntity user);

//    Optional<UserEntity> findByEmail(String email);
}
