package artist_management_system.java.Service;

import artist_management_system.java.Model.UserEntity;

public interface IUserService {

    UserEntity findByEmail(String email);

    int save(UserEntity user);
}
