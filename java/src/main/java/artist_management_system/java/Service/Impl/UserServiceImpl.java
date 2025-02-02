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
    public UserEntity findByEmail(String email, boolean isLoggedIn) {
        List<UserEntity> users = userRepository.findByEmail(email);
        if (!users.isEmpty() && isLoggedIn && users.getFirst().getRole() != null) {
            return users.getFirst();
        } else {
            return null;
        }
    }

    @Override
    public UserEntity save(UserEntity user) {
        boolean result = userRepository.save(user);
        if (result) {
            return user;
        }
        return null;
    }

    @Override
    public UserEntity findById(Integer id) {
        return this.userRepository.findById(id);
    }

    @Override
    public UserEntity update(UserEntity user) {
        boolean result = userRepository.update(user);
        if (result) {
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        return this.userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> findAllByPagination(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return this.userRepository.findAllByPagination(size, offset);
    }

    @Override
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }
}
