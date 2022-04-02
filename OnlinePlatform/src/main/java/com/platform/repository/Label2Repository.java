package com.platform.repository;

import com.platform.model.Label2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Label2Repository extends JpaRepository<Label2, Integer> {

    List<Label2> findAll();
}
