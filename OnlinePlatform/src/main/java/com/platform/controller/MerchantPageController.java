package com.platform.controller;

import com.platform.model.Cart;
import com.platform.model.Label2;
import com.platform.repository.*;
import com.platform.util.ImageUtil;
import com.platform.model.Commodity;
import com.platform.model.Label;
import com.platform.util.CommodityUtil;
import com.platform.util.Constants;
import com.platform.util.LabelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/merchantpage")
@CrossOrigin(origins = "*",maxAge = 3600)
public class MerchantPageController {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private LabelUtil labelUtil;

    @Autowired
    private Label2Repository label2Repository;

    @Autowired
    private CommodityUtil commodityUtil;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 添加标签
     * @param name 标签名
     * @return 添加结果
     */
    @GetMapping("/addlabel")
    @ResponseBody
    public String addLabel(@RequestParam("name") String name){
        Label label = new Label();
        label.setId(labelUtil.newLabelId());
        label.setLabel(name);
        try{
            labelRepository.saveAndFlush(label);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }

    /**
     * 添加商品
     * @param name 商品名字
     * @param price 商品价格
     * @param label2id 商品标签二的id
     * @return 添加结果
     */
    @PostMapping("/addcommodity")
    @ResponseBody
    public String addCommodity(@RequestParam("name") String name,
                               @RequestParam("price") int price,
                               @RequestParam("label2id") int label2id,
                               @RequestParam("img1") MultipartFile img1,
                               @RequestParam("img2") MultipartFile img2,
                               @RequestParam("img3") MultipartFile img3,
                               @RequestParam("meraccount") String meraccount) throws IOException {
        try {
            Commodity commodity = new Commodity();
            commodity.setId(commodityUtil.newCommodityId());
            commodity.setName(name);
            commodity.setPrice(price);
            commodity.setLabel2(label2id);
            commodity.setPict1(imageUtil.imageToByte(img1));
            commodity.setPict2(imageUtil.imageToByte(img2));
            commodity.setPict3(imageUtil.imageToByte(img3));
            commodity.setMeraccount(meraccount);
            commodityRepository.saveAndFlush(commodity);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }

    /**
     * 通过label1的名字获得对应label2的list
     * @param label1name 商品名字
     * @return 返回结果
     */
    @RequestMapping("/getlabel2listbylabel1name")
    @ResponseBody
    public List<Label2> getLabel2ListByLabel1Name(@RequestParam("label1name") String label1name){
        try {
            List<Label> labels = labelRepository.findAllByLabel(label1name);
            return label2Repository.findAllByLabel1(labels.get(0).getId());
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 通过label2的名字获取label2的id
     * @return label2的id
     */
    @RequestMapping("/getlabel2idbylabel2name")
    @ResponseBody
    public int getLabel2idByLabel2Name(@RequestParam("label2name") String label2name){
        try {
            List<Label2> label2s = label2Repository.findAllByLabel2name(label2name);
            return label2s.get(0).getId();
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 通过userid查询商品信息
     * @return 商品list
     */
    @RequestMapping("/getcommoditylistbymerchantuid")
    @ResponseBody
    public List<Commodity> getCommodityListByMerchantUid(@RequestParam("meraccount") String meraccount){
        return commodityRepository.findAllByMeraccount(meraccount);
    }


    /**
     * 获取用户购物车物品
     * @param account
     */
    @GetMapping("/getusercart")
    @ResponseBody
    public List<Commodity> getUserCart(@RequestParam("account") String account) {
        int userid = userRepository.findAllByAccount(account).get(0).getId();
        List<Cart> carts = cartRepository.findAllByUseridAndType(userid, Constants.ADDINTOCART);
        List<Commodity> commodities = new ArrayList<>();
        for(Cart cart : carts){
            commodities.add(commodityRepository.findAllById(cart.getCommodityid()).get(0));
        }
        return commodities;
    }

    /**
     * 获取用户已购买物品
     * @param account
     */
    @GetMapping("/getuserbought")
    @ResponseBody
    public List<Commodity> getUserBought(@RequestParam("account") String account) {
        int userid = userRepository.findAllByAccount(account).get(0).getId();
        List<Cart> carts = cartRepository.findAllByUseridAndType(userid, Constants.BUYCOMMODITY);
        List<Commodity> commodities = new ArrayList<>();
        for(Cart cart : carts){
            commodities.add(commodityRepository.findAllById(cart.getCommodityid()).get(0));
        }
        return commodities;
    }

    /**
     * 获取用户的推荐物品
     * @param account
     */
    @GetMapping("/getuserfavor")
    @ResponseBody
    public List<Commodity> getUserFavor(@RequestParam("account") String account) {
        List<Commodity> commodities = new ArrayList<>();

        // todo
        return commodities;
    }


    /**
     * 删除指定cid的商品
     */
    @Transactional
    @RequestMapping("/deletecombycid")
    @ResponseBody
    public String deleteComByCid(@RequestParam("commodityid") int commodityid){
        try{
            commodityRepository.deleteAllById(commodityid);
            return Constants.SUCCESS;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return Constants.FAILED;
        }
    }

}
