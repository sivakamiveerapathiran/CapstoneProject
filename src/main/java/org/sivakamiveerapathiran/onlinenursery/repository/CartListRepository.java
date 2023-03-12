package org.sivakamiveerapathiran.onlinenursery.repository;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the Repository for the Cartlist entity.
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartListRepository extends JpaRepository<CartList, Integer> {
    List<CartList> findByShoppingCart(ShoppingCart shoppingCart);
    CartList findById(int id);

}
