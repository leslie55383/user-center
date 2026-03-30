package com.node.userCenter.service;
import java.util.Date;

import com.node.userCenter.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author leslie
 * @description:
 * @since 2026/3/25 16:42
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;


    @Test
    void testAddUser() {
        User user = new User();

        user.setUsername("leslie");
        user.setUserAccount("leslie123");
        user.setAvatarUrl("https://tc.tcarcar.com/images/tingche/my.jpg");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("15359795154");
        user.setEmail("leslie5538300@gmail.com");
        user.setUserStatus(1);
        boolean result = userService.save(user);

        System.out.println(user.getId());
        Assertions.assertTrue(result);

    }

    @Test
    void userRegister() {
        String userAccount = "leslie2·~！@";
        String userPassword = "12345789";
        String checkPassword = "12345789";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        System.out.println(result);
    }
}