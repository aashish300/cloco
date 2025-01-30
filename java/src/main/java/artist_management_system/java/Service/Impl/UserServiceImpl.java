package artist_management_system.java.Service.Impl;

import artist_management_system.java.Model.UserEntity;
import artist_management_system.java.Repository.IUserRepository;
import artist_management_system.java.Service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public int save(UserEntity user) {
        return userRepository.save(user);
    }
}
