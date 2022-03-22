package com.platform.repository;

import com.platform.model.Commodity;
import org.springframework.data.repository.CrudRepository;

public interface CommodityRepository extends CrudRepository<Commodity, Integer> {
    Commodity save(Commodity commodity);
}
