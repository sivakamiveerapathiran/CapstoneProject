package org.sivakamiveerapathiran.onlinenursery.service;

import org.sivakamiveerapathiran.onlinenursery.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCartSvcImpl implements ShopCartSvc {

    @Autowired
    private CartListService cartListService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

   /* @Override
    public ShoppingCart ShopCartUpdate(ShoppingCart shoppingCart) {
     List<CartList> cartproductList = cartListService.findByShoppingCart(shoppingCart);
        int cartTotal = 0;
        for (CartList cartList : cartproductList) {
            if (cartList.getProduct().getId() > 0) {
                // cartListService.updateCartItem(cartList);
                cartTotal = cartTotal + cartList.getSubtotal();
            }
        }

        shoppingCart.setTotal(cartTotal);
        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }*/
   /*public List<ShoppingCart> getAllItems() {
        return shoppingCartRepository.findAll();
    }*/
    // return;
}
