package com.platform.util;

import com.platform.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommodityUtil {
    @Autowired
    private CommodityRepository commodityRepository;

    public int newCommodityId(){
        return commodityRepository.findAll().size() + 1;
    }
}
