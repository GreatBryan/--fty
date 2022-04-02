package com.platform.controller;

import com.platform.util.ImageUtil;
import com.platform.model.Commodity;
import com.platform.model.Label;
import com.platform.repository.CommodityRepository;
import com.platform.repository.LabelRepository;
import com.platform.util.CommodityUtil;
import com.platform.util.Constants;
import com.platform.util.LabelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    private CommodityUtil commodityUtil;

    @Autowired
    private ImageUtil imageUtil;

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
                               @RequestParam("img3") MultipartFile img3) throws IOException {
        Commodity commodity = new Commodity();
        commodity.setId(commodityUtil.newCommodityId());
        commodity.setName(name);
        commodity.setPrice(price);
        commodity.setLabel2(label2id);
        commodity.setPict1(imageUtil.imageToByte(img1));
        commodity.setPict2(imageUtil.imageToByte(img2));
        commodity.setPict3(imageUtil.imageToByte(img3));
        try {
            commodityRepository.saveAndFlush(commodity);
            return Constants.SUCCESS;
        }catch (Exception e){
            return Constants.FAILED;
        }
    }


}
