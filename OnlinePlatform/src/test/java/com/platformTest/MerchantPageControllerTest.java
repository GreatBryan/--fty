//package com.platformTest;
//
//import com.platform.controller.MerchantPageController;
//import com.platform.controller.UserPageController;
//import com.platform.model.User;
//import com.platform.util.Constants;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class MerchantPageControllerTest {
//
//    @Autowired
//    private MerchantPageController merchantPageController;
//
//    /**
//     * url test:
//     *          http://127.0.0.1:8080/merchantpage/addlabel?name={}
//     */
//    @Test
//    public void testAddLabel(){
//        String result = merchantPageController.addLabel("testLabel");
//        assertEquals(Constants.SUCCESS, result);
//        result = merchantPageController.addLabel("testLabel");
//        assertEquals(Constants.FAILED, result);
//    }
//
//    /**
//     * url test:
//     *          http://127.0.0.1:8080/merchantpage/addcommodity?name={}&price={}&label1={}&label2={}&label3={}
//     *  建议url测试，该测试函数不能测试少label参数的情况
//     */
//    @Test
//    public void testAddCommodity(){
//        String result = merchantPageController.addCommodity("testName_1", 999, "testLabel1", null, null);
//        assertEquals(Constants.SUCCESS, result);
//        result = merchantPageController.addCommodity("testName_2", 999, "testLabel1", "手机", null);
//        assertEquals(Constants.SUCCESS, result);
//        result = merchantPageController.addCommodity("testName_3", 999, "testLabel1", "手机", "游戏");
//        assertEquals(Constants.SUCCESS, result);
//        result = merchantPageController.addCommodity("testName_1", 999, "testLabel1", "手机", "游戏");
//        assertEquals(Constants.FAILED, result);
//    }
//
//}
