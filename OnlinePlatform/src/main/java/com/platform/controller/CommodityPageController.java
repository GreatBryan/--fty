package com.platform.controller;

import com.platform.repository.CommodityRepository;
import com.platform.repository.Label2Repository;
import com.platform.repository.LabelRepository;
import com.platform.model.Cart;
import com.platform.repository.CartRepository;
import com.platform.util.CartUtil;
import com.platform.util.Constants;
import com.platform.util.DateUtil;
import com.platform.model.Commodity;
import com.platform.model.Label;
import com.platform.model.Label2;
import com.platform.util.LabelUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/commodity")
@CrossOrigin(origins = "*",maxAge = 3600)
public class CommodityPageController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private LabelUtil labelUtil;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private CartUtil cartUtil;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private Label2Repository label2Repository;

    /**
     * 加入购物车
     * @param commodityid 商品id的主键
     * @param userid 用户id的主键
     * @return 加入结果
     */
    @RequestMapping("/addcart")
    @ResponseBody
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
    @ResponseBody
    public String buyCommodity(@RequestParam("commodityid") int commodityid,
                          @RequestParam("userid") int userid){
        return updateCartByCommodityidAndUserid(commodityid, userid, Constants.BUYCOMMODITY);
    }

    /**
     * 删除购物车记录
     * @param userid
     * @param commodityid
     * @param type
     * @return 删除结果
     */
    @RequestMapping("/deletecart")
    @ResponseBody
    @Transactional
    public String deleteCart(@RequestParam("userid") int userid,
                             @RequestParam("commodityid") int commodityid,
                             @RequestParam("type") int type){
        try{
            cartRepository.deleteAllByUseridAndCommodityidAndType(userid, commodityid, type);
            return Constants.SUCCESS;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Constants.FAILED;
        }
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

    /**
     * 获取商品一级标签
     */
    @RequestMapping("/getalllabel1")
    @ResponseBody
    public String getAllLabel1() throws JSONException {
        List<Label> labels = labelRepository.findAll();
        List<Label2> label2s = label2Repository.findAll();
        JSONObject json = new JSONObject();
        for(int i = 0; i < labels.size(); i++){
            JSONObject tmp1 = new JSONObject();
            JSONObject tmp2 = new JSONObject();
            for(int j = 0; j < label2s.size(); j++){
                if(label2s.get(j).getLabel1() == labels.get(i).getId()){
                    JSONObject tmp3 = new JSONObject();
                    tmp3.put("label2id", label2s.get(j).getId());
                    tmp3.put("label2name", label2s.get(j).getLabel2name());
                    tmp2.put(String.valueOf(j), tmp3);
                }
            }
            tmp1.put("label2List", tmp2);
            tmp1.put("label1name", labels.get(i).getLabel());
            tmp1.put("label1id",labels.get(i).getId());
            json.put(String.valueOf(i), tmp1);
        }
        return json.toString();
    }

    /**
     * 根据标签2获取商品信息
     */
    @RequestMapping("/getcombylabel2")
    @ResponseBody
    public List<Commodity> getCommodityByLabel2(@RequestParam("label2id") int label2id){
        return commodityRepository.findAllByLabel2(label2id);
    }

    /**
     * 根据商品id获取商品图片1
     */
    @RequestMapping("/getpict1byid")
    @ResponseBody
    public byte[] getPicture1ById(@RequestParam("commodityid") int commodityid){
        return commodityRepository.findAllById(commodityid).get(0).getPict1();
    }

    /**
     * 根据商品id获取商品图片2
     */
    @RequestMapping("/getpict2byid")
    @ResponseBody
    public byte[] getPicture2ById(@RequestParam("commodityid") int commodityid){
        return commodityRepository.findAllById(commodityid).get(0).getPict2();
    }

    /**
     * 根据商品id获取商品图片1
     */
    @RequestMapping("/getpict3byid")
    @ResponseBody
    public byte[] getPicture3ById(@RequestParam("commodityid") int commodityid){
        return commodityRepository.findAllById(commodityid).get(0).getPict3();
    }

    /**
     * 新增标签1
     */
    @RequestMapping("/newlabel1")
    @ResponseBody
    public String newLabel1(@RequestParam("label1") String label1){
        Label label = new Label();
        label.setId(labelUtil.newLabelId());
        label.setLabel(label1);
        try{
            labelRepository.saveAndFlush(label);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }

    /**
     * 新增标签2
     */
    @RequestMapping("/newlabel2")
    @ResponseBody
    public String newLabel2(@RequestParam("label1") String label1,
                            @RequestParam("label2") String label2){
        try{
            Label label = labelRepository.findAllByLabel(label1).get(0);
            Label2 label2new = new Label2();
            label2new.setId(labelUtil.newLabel2Id());
            label2new.setLabel1(label.getId());
            label2new.setLabel2name(label2);
            label2Repository.saveAndFlush(label2new);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }

}
