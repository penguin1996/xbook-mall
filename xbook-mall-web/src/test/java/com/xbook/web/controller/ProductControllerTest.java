package com.xbook.web.controller;

import com.github.pagehelper.PageInfo;
import com.xbook.entity.product.ProductDetail;
import com.xbook.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(value = ProductController.class)
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ProductControllerTest {

    @MockBean
    ProductService productService;

    MockMvc mockMvc;

    @Autowired
    ProductController productController;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        MockitoAnnotations.openMocks(productService);
    }

    @Test
    public void testProductList() throws Exception {

        PageInfo pageInfo = new PageInfo();
        //Mockito.verify(productService);
        given(productService.pageProduct(Mockito.anyString(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).willReturn(pageInfo);
        //when(productService.pageProduct(Mockito.anyString(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(pageInfo);
        mockMvc.perform(get("/product/list")
                        //.param("keyword","")
                        .param("categoryId","100008")
                        .param("pageNum","1")
                        .param("pageSize","20")
                        .param("orderBy","default")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testDetail() throws Exception {

        given(productService.queryProductDetail(Mockito.anyInt())).willReturn(new ProductDetail());
        mockMvc.perform(get("/product//detail")
                        .param("productId","26")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
