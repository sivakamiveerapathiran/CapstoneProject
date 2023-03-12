package org.sivakamiveerapathiran.onlinenursery.service;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the implementation for the Cartlist Interface.
 *
 * Methods:
 * addProductToCartList -  This method is used to add a product to user shopping Cart
 ***************************/
import lombok.extern.slf4j.Slf4j;
import org.sivakamiveerapathiran.onlinenursery.repository.CartListRepository;
import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartListServiceImpl implements CartListService {

    @Autowired
    private CartListRepository cartListRepo;
    @Autowired
    private UserService userService;

    @Override
    public List<CartList> findByShoppingCart(ShoppingCart shoppingCart) {
        List<CartList> cartlist=cartListRepo.findByShoppingCart(shoppingCart);

        return cartlist;
    }


    @Override
    public CartList addProductToCartList(Product product, User user, int amount) {
        CartList carItem = null;
        boolean iscartempty=false;
        boolean isitemfound = false;
        // List<ShoppingCart> tmpshopcart=user.getShoppingCart();
        ShoppingCart tmpshopcart = userService.findByUserId(user);
        log.info("Inside addProductToCartList Method");

         if(tmpshopcart!=null) {
             log.info("Inside addItemtoShoppingCart Method");
             ShoppingCart shpcart=tmpshopcart;

             log.debug("shpcart ID: "+ shpcart.getId());
             List<CartList> cartList = findByShoppingCart(shpcart);
            for (CartList cartItem : cartList) {
                 System.out.println(cartItem.getProduct().getId());
                 if (product.getId() == cartItem.getProduct().getId()) {

                     log.debug("cartItem.getProduct().getId(): "+ cartItem.getProduct().getId());
                     cartItem.setAmount(cartItem.getAmount() + amount);
                     cartItem.setSubtotal(product.getPrice() * amount);
                     cartListRepo.save(cartItem);
                     carItem = cartItem;
                     isitemfound = true;
                     // return cartItem;

                 }
             }
         }
         else
         {
             iscartempty=true;
         }
        log.debug("iscartempty: "+iscartempty);
        log.debug("isitemfound: "+ isitemfound);
        if ((!isitemfound)) {
            ShoppingCart shopcart = userService.findByUserId(user);

            CartList cartListItem = new CartList();

            log.debug("shopcart 2: "+shopcart.getId());
            cartListItem.setShoppingCart(shopcart);
            cartListItem.setProduct(product);

            cartListItem.setAmount(amount);
            cartListItem.setSubtotal(product.getPrice() * amount);
            cartListItem = cartListRepo.save(cartListItem);
                // return cartItem;
            carItem=cartListItem;

            }
        return carItem;
    }

    public List<CartList> getAllProducts(ShoppingCart scart) {

        return cartListRepo.findByShoppingCart(scart);
    }

    @Override
    public CartList findById(int id) {
        return cartListRepo.findById(id);

    }




}