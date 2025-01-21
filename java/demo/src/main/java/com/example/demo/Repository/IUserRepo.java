package com.example.demo.Repository;

import com.example.demo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;

public interface IUserRepo extends JpaRepository<User, Long> {
}
