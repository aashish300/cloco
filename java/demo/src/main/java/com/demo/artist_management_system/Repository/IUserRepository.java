package com.demo.artist_management_system.Repository;

import com.demo.artist_management_system.Model.UserEntity;
import com.demo.artist_management_system.Repository.BaseRepository.IBaseRepository;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserRepository extends IBaseRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
}
