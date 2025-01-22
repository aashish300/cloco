package com.demo.artist_management_system.Service;

import com.demo.artist_management_system.Model.UserEntity;

public interface IUserService {

    UserEntity findByEmail(String email);
}
