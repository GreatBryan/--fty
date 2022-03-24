package com.platform.util;

import com.platform.model.User;
import com.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserUtil {

    @Autowired
    private UserRepository userRepository;

    public int newUserId(){
        return userRepository.findAll().size() + 1;
    }

}
