package com.platform.repository;

import com.platform.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {
    Commodity saveAndFlush(Commodity commodity);

    List<Commodity> findAll();

    List<Commodity> findAllByMeraccount(String Meraccount);

    void deleteAllById(int cid);

    List<Commodity> findAllByLabel2(int label2id);

    List<Commodity> findAllById(int id);

    @Query(value="select max(commodity.id) from commodity", nativeQuery = true)
    int findMaxId();
}