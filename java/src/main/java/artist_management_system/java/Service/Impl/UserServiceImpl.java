package artist_management_system.java.Service.Impl;

import artist_management_system.java.Exception.NotFoundException;
import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.IUserRepository;
import artist_management_system.java.Service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByEmail(String email) {
        List<UserEntity> users = userRepository.findByEmail(email);
        if (!users.isEmpty()) {
            return users.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public UserEntity save(UserEntity user) {
        int result = userRepository.save(user);
        if (result == 1) {
            return user;
        }
        return null;
    }
}
