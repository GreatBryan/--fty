package com.platform.util;

import com.platform.model.Label;
import com.platform.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabelUtil {
    @Autowired
    private LabelRepository labelRepository;

    public int findLabelIdByLabel(String name){
        if(name == null){
            return labelRepository.findByLabel(Constants.DEFAULTLABEL).getId();
        }
        Label label = labelRepository.findByLabel(name);
        if(label != null){
            return label.getId();
        }else {
            Label newLabel = new Label();
            newLabel.setLabel(name);
            return labelRepository.save(newLabel).getId();
        }
    }
}
