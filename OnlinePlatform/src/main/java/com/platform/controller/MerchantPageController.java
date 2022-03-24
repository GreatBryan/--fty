package com.platform.controller;

import com.platform.model.Commodity;
import com.platform.model.Label;
import com.platform.repository.CommodityRepository;
import com.platform.repository.LabelRepository;
import com.platform.util.CommodityUtil;
import com.platform.util.Constants;
import com.platform.util.LabelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchantpage")
public class MerchantPageController {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private LabelUtil labelUtil;

    @Autowired
    private CommodityUtil commodityUtil;

    /**
     * 添加标签
     * @param name 标签名
     * @return 添加结果
     */
    @GetMapping("/addlabel")
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
     * @param label1 商品标签一
     * @param label2 商品标签二，可选
     * @param label3 商品标签三，可选
     * @return 添加结果
     */
    @GetMapping("/addcommodity")
    public String addCommodity(@RequestParam("name") String name,
                               @RequestParam("price") int price,
                               @RequestParam("label1") String label1,
                               @RequestParam(value = "label2", required = false) String label2,
                               @RequestParam(value = "label3", required = false) String label3){
        Commodity commodity = new Commodity();
        commodity.setId(commodityUtil.newCommodityId());
        commodity.setName(name);
        commodity.setPrice(price);
        commodity.setLabel1(labelUtil.findLabelIdByLabel(label1));
        commodity.setLabel2(labelUtil.findLabelIdByLabel(label2));
        commodity.setLabel3(labelUtil.findLabelIdByLabel(label3));
        try {
            commodityRepository.saveAndFlush(commodity);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }


}
