package com.chen.securitydemo02;

import com.chen.dao.UserDao;

import com.chen.po.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Securitydemo02ApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        Users user = userDao.selectById(1);
        System.out.println(user);
    }

}
