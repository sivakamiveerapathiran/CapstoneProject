package org.sivakamiveerapathiran.onlinenursery.service;

import org.sivakamiveerapathiran.onlinenursery.repository.CartListRepository;
import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        System.out.println("1");

         if(tmpshopcart!=null) {
             System.out.println("2");

             ShoppingCart shpcart=tmpshopcart;

             System.out.println("XXshpcart ID: "+ shpcart.getId());
             List<CartList> cartList = findByShoppingCart(shpcart);
            for (CartList cartItem : cartList) {
                System.out.println("3");
                 System.out.println(cartItem.getProduct().getId());
                 if (product.getId() == cartItem.getProduct().getId()) {
                     System.out.println("4");
                     System.out.println("Indside:" + cartItem.getProduct().getId());
                     cartItem.setAmount(cartItem.getAmount() + amount);
                     cartItem.setSubtotal(product.getPrice() * amount);
                     cartListRepo.save(cartItem);
                     carItem = cartItem;
                     isitemfound = true;
                     // return cartItem;

                 }
                System.out.println("5");
             }
             System.out.println("6");
         }
         else
         {
             System.out.println("7");
             iscartempty=true;
         }
        System.out.println("iscartempty: "+iscartempty);
        System.out.println("isitemfound: "+ isitemfound);
        if ((!isitemfound)) {
            System.out.println(" new Indside:");
            ShoppingCart shopcart = userService.findByUserId(user);

            CartList cartListItem = new CartList();

            System.out.println("shopcart:"+shopcart.getId());
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