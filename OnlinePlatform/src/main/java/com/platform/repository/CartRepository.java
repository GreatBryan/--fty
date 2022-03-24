package com.platform.repository;

import com.platform.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart saveAndFlush(Cart cart);

    List<Cart> findAll();

    List<Cart> findAllByUseridAndCommodityidAndType(int userid, int commodityid, int type);

}
