package artist_management_system.java.Service;

import artist_management_system.java.Model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserEntity findByEmail(String email);

    UserEntity save(UserEntity user);
}
