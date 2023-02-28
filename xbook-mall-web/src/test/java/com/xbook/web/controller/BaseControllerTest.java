package com.xbook.web.controller;

import com.alibaba.fastjson.JSON;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.redis.key.UserKey;
import com.xbook.common.utils.CookieUtil;
import com.xbook.entity.user.User;
import com.xbook.redis.service.RedisService;
import com.xbook.user.service.exception.UserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.Cookie;

import static org.mockito.Mockito.when;

@WebMvcTest(BaseController.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(CookieUtil.class)
public class BaseControllerTest {

    @Mock
    RedisService redisService;

    @InjectMocks
    BaseController baseController;

    @Test
    public void testGetCurrentUserId(){
        String cookie = "c0bd4c287a1a4465a80eb339916e19ef";
        MockHttpServletRequest request = new MockHttpServletRequest();
        String session = "{\"id\":53,\"username\":\"root\"}";
        String cookieName = SysConstant.LOGIN_TOKEN;
        request.setCookies(new Cookie(cookieName,cookie));
        PowerMockito.mockStatic(CookieUtil.class);
        PowerMockito.when(CookieUtil.getCookieValue(request, cookieName)).thenReturn(cookie);
        when(redisService.get(UserKey.loginUser,cookie)).thenReturn(session);
        Integer currentUserId = baseController.getCurrentUserId(request);
        Assert.assertEquals(currentUserId, JSON.parseObject(session,User.class).getId());
    }

   @Test(expected = UserException.class)
    public void testGetCurrentUserIdCookieIsNull(){
        String cookie = "";
        MockHttpServletRequest request = new MockHttpServletRequest();
        String session = "{\"id\":53,\"username\":\"root\"}";
        String cookieName = SysConstant.LOGIN_TOKEN;
        request.setCookies(new Cookie(cookieName,cookie));
        PowerMockito.mockStatic(CookieUtil.class);
        PowerMockito.when(CookieUtil.getCookieValue(request, cookieName)).thenReturn(cookie);
        when(redisService.get(UserKey.loginUser,cookie)).thenReturn(session);
        baseController.getCurrentUserId(request);
    }

    @Test(expected = UserException.class)
    public void testGetCurrentUserIdSessionIsNull(){
        String cookie = "c0bd4c287a1a4465a80eb339916e19ef";
        MockHttpServletRequest request = new MockHttpServletRequest();
        String session = "";
        String cookieName = SysConstant.LOGIN_TOKEN;
        request.setCookies(new Cookie(cookieName,cookie));
        PowerMockito.mockStatic(CookieUtil.class);
        PowerMockito.when(CookieUtil.getCookieValue(request, cookieName)).thenReturn(cookie);
        when(redisService.get(UserKey.loginUser,cookie)).thenReturn(session);
        baseController.getCurrentUserId(request);
    }
}
