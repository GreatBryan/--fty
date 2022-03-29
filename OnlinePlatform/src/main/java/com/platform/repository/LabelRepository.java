package com.platform.repository;

import com.platform.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Integer> {

    List<Label> findAll();

    Label saveAndFlush(Label label);

    Label findByLabel(String name);

    @Query(value="select max(label.id) from label", nativeQuery = true)
    int findMaxId();
}
