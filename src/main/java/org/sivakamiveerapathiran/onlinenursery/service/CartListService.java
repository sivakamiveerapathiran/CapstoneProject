package org.sivakamiveerapathiran.onlinenursery.service;

import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.sivakamiveerapathiran.onlinenursery.models.User;

import java.util.List;

public interface CartListService {

    List<CartList> findByShoppingCart(ShoppingCart shoppingCart);

    CartList addProductToCartList(Product product, User user, int amount);
    public List<CartList> getAllProducts(ShoppingCart scart);
   public CartList findById(int  id);




}
