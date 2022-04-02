package com.platform.repository;

import java.util.List;
import com.platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAll();

    List<User> findAllByAccount(String account);

    User saveAndFlush(User user);

    @Query(value="select max(users.id) from users", nativeQuery = true)
    int findMaxId();
}
