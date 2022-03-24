package com.platformTest;

import com.platform.controller.CommodityPageController;
import com.platform.util.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommodityPageControllerTest {

    @Autowired
    private CommodityPageController commodityPageController;

    /**
     * url test:
     *          http://127.0.0.1:8080/commodity/addcart?commodityid={}&userid={}
     *          此测试需要去cart表中手动确定
     */
    @Test
    public void testAddCart(){
        String result = commodityPageController.addCart(1, 1);
        //此测试需要去cart表中手动确定
        assertEquals(Constants.SUCCESS, result);
    }

    /**
     * url test:
     *          http://127.0.0.1:8080/commodity/buycommodity?commodityid={}&userid={}
     *          此测试需要去cart表中手动确定
     */
    @Test
    public void testBuyCommodity(){
        String result = commodityPageController.buyCommodity(1, 1);
        //此测试需要去cart表中手动确定
        assertEquals(Constants.SUCCESS, result);
    }
}
