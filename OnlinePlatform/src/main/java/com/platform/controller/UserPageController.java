package com.platform.controller;

import com.platform.model.User;
import com.platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userpage")
public class UserPageController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 通过账号查询用户
     * @param account 账号
     * @return User
     */
    @GetMapping("/user")
    public User getUserByAccount(@RequestParam("account") String account){
        List<User> users = userRepository.findAllByAccount(account);
        if(users.size() == 1){
            return users.get(0);
        }else {
            return new User();
        }
    }

}
