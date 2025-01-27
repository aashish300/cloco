package com.demo.artist_management_system.Repository;

import com.demo.artist_management_system.Model.UserEntity;
import com.demo.artist_management_system.Repository.BaseRepository.IBaseRepository;

public interface IUserRepository extends IBaseRepository<UserEntity, Integer> {

    UserEntity findByEmail(String email);
}
