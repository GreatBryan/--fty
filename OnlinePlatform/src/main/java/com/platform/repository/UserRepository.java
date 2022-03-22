package com.platform.repository;

import java.util.List;
import com.platform.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>{

    List<User> findAllByAccount(String account);

}
