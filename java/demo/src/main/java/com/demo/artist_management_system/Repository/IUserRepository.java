package com.demo.artist_management_system.Repository;

import com.demo.artist_management_system.Model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserRepository {

    UserEntity findByEmail(String email);

    Page<UserEntity> findAll(Pageable pageable);
}
