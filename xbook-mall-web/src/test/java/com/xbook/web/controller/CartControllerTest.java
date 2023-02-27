package com.xbook.web.controller;


import com.xbook.cart.service.CartService;
import com.xbook.common.constant.SysConstant;
import com.xbook.common.utils.CookieUtil;
import com.xbook.redis.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(CookieUtil.class)
public class CartControllerTest{

    @InjectMocks
    private CartController cartController;

    private MockMvc mockMvc;

    @Mock
    CartService cartService;

    @Mock
    RedisService redisService;

    @Mock
    BaseController baseController;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    /**
     * 获取购物车商品购买数量
     * @param 
     * @return
     */
    @Test
    public void getCartCount() throws Exception {
        Integer result = 1;
        String session = "session";
        String cookie = "cookie";
        MockHttpServletRequest request = new MockHttpServletRequest();
        //HttpServletRequest request = mock(HttpServletRequest.class);

        String cookieName = SysConstant.LOGIN_TOKEN;
        request.setCookies(new Cookie(cookieName,"1"));
        PowerMockito.mockStatic(CookieUtil.class);
        PowerMockito.when(CookieUtil.getCookieValue(request, cookieName)).thenReturn(cookie);
        when(baseController.getCurrentUserId(request)).thenReturn(result);
        when(redisService.get(any(),anyString())).thenReturn(session);
        when(cartService.getCartCount(anyInt())).thenReturn(result);
        mockMvc.perform(get("/cart/getCartProductCount").contentType(MediaType.APPLICATION_JSON).cookie(request.getCookies()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 添加到购物车
     * @return
     */
    @Test
    public void addToCart()  throws Exception {

        mockMvc.perform(get("/cart/add").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }

    /**
     * 获取购物车信息
     * @return
     */
    @Test
    public void getCartList() throws Exception {
        mockMvc.perform(get("/cart/list").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }


    /**
     * 购物车全选
     */
    @Test
    public void selectAll() throws Exception {
        mockMvc.perform(get("/cart/selectAll").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }

    /**
     * 购物车全不选
     */
    @Test
    public void unSelectAll() throws Exception {
        mockMvc.perform(get("/cart/unSelectAll").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }

    /**
     * 购物车选中某个商品
     */
    @Test
    public void select() throws Exception {
        mockMvc.perform(get("/cart/select").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }

    /**
     * 购物车取消选中某个商品
     */
    @Test
    public void unSelect() throws Exception {
        mockMvc.perform(get("/cart/unSelect").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }

    /**
     * 更新购物车某个产品数量
     * @return
     */
    @Test
    public void update() throws Exception {
        mockMvc.perform(get("/cart/update").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }


    /**
     * 移除购物车某个产品
     */
    @Test
    public void deleteProduct() throws Exception {
        mockMvc.perform(get("/cart/deleteProduct").contentType(MediaType.APPLICATION_JSON)
                .contentType("2")).andExpect(status().isCreated()).andDo(print());
    }
}
