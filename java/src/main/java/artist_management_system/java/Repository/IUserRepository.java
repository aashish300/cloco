package artist_management_system.java.Repository;

import artist_management_system.java.Model.UserEntity;

public interface IUserRepository {

    UserEntity findByEmail(String email);

    UserEntity save(UserEntity user);
}
