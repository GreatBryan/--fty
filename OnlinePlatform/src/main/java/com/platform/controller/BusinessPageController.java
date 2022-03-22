package com.platform.controller;

import com.platform.model.Commodity;
import com.platform.model.Label;
import com.platform.repository.CommodityRepository;
import com.platform.repository.LabelRepository;
import com.platform.util.Constants;
import com.platform.util.LabelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/businesspage")
public class BusinessPageController {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private CommodityRepository commodityRepository;

    @Autowired
    private LabelUtil labelUtil;

    /**
     * 添加标签
     * @param name 标签名
     * @return 添加结果
     */
    @GetMapping("/addlabel")
    public String addLabel(@RequestParam("name") String name){
        Label label = new Label();
        label.setLabel(name);
        try{
            labelRepository.save(label);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }

    /**
     * 添加商品
     * @param name 商品名字
     * @param price 商品价格
     * @param label_1 商品标签一
     * @param label_2 商品标签二，可选
     * @param label_3 商品标签三，可选
     * @return 添加结果
     */
    @GetMapping("/addcommodity")
    public String addCommodity(@RequestParam("name") String name,
                               @RequestParam("price") int price,
                               @RequestParam("label_1") String label_1,
                               @RequestParam(value = "label_2", required = false) String label_2,
                               @RequestParam(value = "label_3", required = false) String label_3){
        Commodity commodity = new Commodity();
        commodity.setName(name);
        commodity.setPrice(price);
        commodity.setLabel_1(labelUtil.findLabelIdByLabel(label_1));
        commodity.setLabel_2(labelUtil.findLabelIdByLabel(label_2));
        commodity.setLabel_3(labelUtil.findLabelIdByLabel(label_3));
        try {
            commodityRepository.save(commodity);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }


}
