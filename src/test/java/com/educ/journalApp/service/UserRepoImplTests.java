package com.educ.journalApp.service;

import com.educ.journalApp.Repository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserRepoImplTests {

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Test
    public void Test(){
        Assertions.assertNotNull(userRepoImpl.getUserForSA());
    }
}
