package com.platform.repository;

import com.platform.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {
    Commodity saveAndFlush(Commodity commodity);

    List<Commodity> findAll();
}