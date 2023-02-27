package com.xbook.user.service.impl;

import com.xbook.entity.user.User;
import com.xbook.user.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;


@WebAppConfiguration
public class UserServiceImplTest {

    @Resource
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public void setUp(){
        Mockito.when(userService.getUserInfo(1)).thenReturn(new User());
    }

    @Test
    public void testGetUserInfo(){

    }
}
