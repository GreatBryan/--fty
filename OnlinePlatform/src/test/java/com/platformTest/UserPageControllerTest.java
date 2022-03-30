package com.platformTest;

import com.platform.controller.UserPageController;
import com.platform.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserPageControllerTest {

    @Autowired
    private UserPageController userPageController;

    /**
     * url test:
     *          http://127.0.0.1:8080/userpage/user?account={}
     */
    @Test
    public void testGetUserByAccount(){
        List<User> user = userPageController.getUserByAccount("1368111909");
        assertEquals(1, user.get(0).getId());
        assertEquals("fty", user.get(0).getName());
        assertEquals("1368111909", user.get(0).getAccount());
        assertEquals("123456789", user.get(0).getPassword());
        assertEquals(1, user.get(0).getRole());
        byte[] picture = new byte[1];
        assertEquals(null, user.get(0).getPicture());

    }

    //图片测试见同级目录的tmp文件，包含了转换工具

}
