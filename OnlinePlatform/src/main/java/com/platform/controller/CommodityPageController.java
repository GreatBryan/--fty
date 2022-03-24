package com.platform.controller;

import com.platform.model.Cart;
import com.platform.repository.CartRepository;
import com.platform.util.CartUtil;
import com.platform.util.Constants;
import com.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityPageController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private CartUtil cartUtil;

    /**
     * 加入购物车
     * @param commodityid 商品id的主键
     * @param userid 用户id的主键
     * @return 加入结果
     */
    @RequestMapping("/addcart")
    public String addCart(@RequestParam("commodityid") int commodityid,
                       @RequestParam("userid") int userid){
        return updateCartByCommodityidAndUserid(commodityid, userid, Constants.ADDINTOCART);
    }

    /**
     * 购买商品
     * @param commodityid 商品id的主键
     * @param userid 用户id的主键
     * @return 购买结果
     */
    @RequestMapping("/buycommodity")
    public String buyCommodity(@RequestParam("commodityid") int commodityid,
                          @RequestParam("userid") int userid){
        return updateCartByCommodityidAndUserid(commodityid, userid, Constants.BUYCOMMODITY);
    }

    public String updateCartByCommodityidAndUserid(int commodityid, int userid, int type){
        List<Cart> queryCartList = cartRepository.findAllByUseridAndCommodityidAndType(userid, commodityid, type);
        // cart表中还没有该用户添加该商品的记录
        if(queryCartList.size() == 0){
            Cart cart = new Cart();
            cart.setId(cartUtil.newCartId());
            cart.setCommodityid(commodityid);
            cart.setUserid(userid);
            cart.setType(type);
            cart.setUpdatetime(dateUtil.getCurrentTimestamp());
            cart.setTimes(1);
            try {
                cartRepository.saveAndFlush(cart);
                return Constants.SUCCESS;
            }catch (Exception e){
                return Constants.FAILED;
            }
        }// 购物车已有该用户添加该商品的记录
        else{
            Cart queryCart = queryCartList.get(0);
            queryCart.setUpdatetime(dateUtil.getCurrentTimestamp());
            queryCart.setTimes(queryCart.getTimes() + 1);
            try {
                cartRepository.saveAndFlush(queryCart);
                return Constants.SUCCESS;
            }catch (Exception e){
                return Constants.FAILED;
            }
        }
    }
}
