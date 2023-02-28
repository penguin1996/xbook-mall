package com.xbook.user.service.impl;

import com.xbook.dao.user.ShippingMapper;
import com.xbook.entity.user.Shipping;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@RunWith(PowerMockRunner.class)
@SpringBootTest
public class AddressServiceImplTest {

    @InjectMocks
    AddressServiceImpl addressService;

    @Mock
    ShippingMapper shippingMapper;

    @Test
    public void testSaveAddress(){
        PowerMockito.when(shippingMapper.insert(Mockito.any())).thenReturn(1);
        Map map = addressService.saveAddress(new Shipping(), 53);
        Assert.assertEquals(1,map.get("shippingId"));
    }
}
