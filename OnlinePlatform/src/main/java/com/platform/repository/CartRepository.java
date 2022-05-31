package com.platform.repository;

import com.platform.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart saveAndFlush(Cart cart);

    List<Cart> findAll();

    List<Cart> findAllByUseridAndType(int userid, int type);

    void deleteAllByUseridAndCommodityidAndType(int userid, int commodityid, int type);

    List<Cart> findAllByUseridAndCommodityidAndType(int userid, int commodityid, int type);

    //调用数据库自定义函数
    @Query(value="select max(cart.id) from cart", nativeQuery = true)
    int findMaxId();

}
