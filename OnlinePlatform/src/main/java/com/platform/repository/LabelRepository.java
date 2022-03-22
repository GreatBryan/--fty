package com.platform.repository;

import com.platform.model.Label;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabelRepository extends CrudRepository<Label, Integer> {

    Label save(Label label);

    Label findByLabel(String name);
}
