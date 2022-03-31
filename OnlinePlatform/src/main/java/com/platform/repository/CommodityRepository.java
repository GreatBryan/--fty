package com.platform.repository;

import com.platform.util.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {
    Commodity saveAndFlush(Commodity commodity);

    List<Commodity> findAll();

    @Query(value="select max(commodity.id) from commodity", nativeQuery = true)
    int findMaxId();
}