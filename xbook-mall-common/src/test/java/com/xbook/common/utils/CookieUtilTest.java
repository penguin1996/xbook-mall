package com.xbook.common.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.Cookie;

//参考：https://blog.csdn.net/qq_41055045/article/details/126977753
@RunWith(MockitoJUnitRunner.class)
public class CookieUtilTest {

    @Test
    public void getCookieValue(){

        String expectMock = "MockCookies";
        MockedStatic<CookieUtil> cookieUtilMockedStatic = Mockito.mockStatic(CookieUtil.class);
        cookieUtilMockedStatic.when(()->CookieUtil.getCookieValue(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(expectMock);

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setCookies(new Cookie("test","test"));
        String test = CookieUtil.getCookieValue(mockHttpServletRequest, "test", false);
        Assert.assertEquals(expectMock,test);
        cookieUtilMockedStatic.close();
    }
}
