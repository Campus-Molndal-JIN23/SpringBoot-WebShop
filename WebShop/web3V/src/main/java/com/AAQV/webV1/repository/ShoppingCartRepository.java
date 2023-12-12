package com.AAQV.webV1.repository;

import com.AAQV.webV1.model.ApplicationUser;
import com.AAQV.webV1.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findAllByUserOrderByCreatedDateDesc(ApplicationUser user);

    List<ShoppingCart> deleteByUser(ApplicationUser user);
}
