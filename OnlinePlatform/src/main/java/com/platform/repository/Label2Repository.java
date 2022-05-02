package com.platform.repository;

import com.platform.model.Label;
import com.platform.model.Label2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Label2Repository extends JpaRepository<Label2, Integer> {

    List<Label2> findAll();

    List<Label2> findAllByLabel2name(String label2name);

    Label2 saveAndFlush(Label2 label2);

    List<Label2> findAllByLabel1(int label1id);

    @Query(value="select max(label2.id) from label2", nativeQuery = true)
    int findMaxId();
}
