package com.xbook.web.controller;


import com.xbook.cart.service.CartService;
import com.xbook.common.constant.SysConstant;
import com.xbook.redis.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.Cookie;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(CartController.class)
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//@PrepareForTest(CookieUtil.class)
//@RunWith(PowerMockRunner.class)
//@SpringBootTest
@WebAppConfiguration
public class CartControllerTest{

    @MockBean
    CartService cartService;

    @MockBean
    RedisService redisService;

    MockMvc mockMvc;

    @Autowired
    CartController cartController;

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
    public void testGetCartProductCount() throws Exception {
        Integer result = 1;
        String session = "session";
        String cookie = "c0bd4c287a1a4465a80eb339916e19ef";
        MockHttpServletRequest request = new MockHttpServletRequest();
        //HttpServletRequest request = mock(HttpServletRequest.class);

        String cookieName = SysConstant.LOGIN_TOKEN;
        request.setCookies(new Cookie(cookieName,cookie));
        /*PowerMockito.mockStatic(CookieUtil.class);
        PowerMockito.when(CookieUtil.getCookieValue(request, cookieName)).thenReturn(cookie);*/
        //when(baseController.getCurrentUserId(request)).thenReturn(result);
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