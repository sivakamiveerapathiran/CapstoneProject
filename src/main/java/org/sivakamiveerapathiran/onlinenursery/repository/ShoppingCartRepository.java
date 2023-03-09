package org.sivakamiveerapathiran.onlinenursery.repository;

import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    @Query(value = "select * from shoppingcart where user_id= :id and status='A'", nativeQuery = true)
    public List<ShoppingCart> findByUserID(@Param("id") long userID);


}
