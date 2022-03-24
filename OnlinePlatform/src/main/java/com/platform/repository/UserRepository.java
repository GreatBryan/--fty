package com.platform.repository;

import java.util.List;
import com.platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    List<User> findAllByAccount(String account);

}
