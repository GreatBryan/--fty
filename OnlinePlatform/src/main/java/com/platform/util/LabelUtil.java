package com.platform.util;

import com.platform.model.Label;
import com.platform.repository.Label2Repository;
import com.platform.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabelUtil {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private Label2Repository label2Repository;

    //通过标签名获取标签的主键id，若表中没有此id，则插入新标签并返回其id。
    public int findLabelIdByLabel(String name){
        if(name == null){
            return labelRepository.findByLabel(Constants.DEFAULTLABEL).getId();
        }
        Label label = labelRepository.findByLabel(name);
        if(label != null){
            return label.getId();
        }else {
            Label newLabel = new Label();
            newLabel.setId(newLabelId());
            newLabel.setLabel(name);
            return labelRepository.saveAndFlush(newLabel).getId();
        }
    }

    public int newLabelId(){
        try{
            return labelRepository.findMaxId() + 1;
        } catch (Exception e){
            return 1;
        }
    }

    public int newLabel2Id(){
        try{
            return label2Repository.findMaxId() + 1;
        } catch (Exception e){
            return 1;
        }
    }

}
