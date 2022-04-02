package com.platform.util;

import com.platform.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommodityUtil {
    @Autowired
    private CommodityRepository commodityRepository;

    public int newCommodityId(){
        try{
            return commodityRepository.findMaxId() + 1;
        } catch (Exception e){
            return 1;
        }
    }
}
