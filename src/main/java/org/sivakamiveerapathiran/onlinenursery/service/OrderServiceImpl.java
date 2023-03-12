package org.sivakamiveerapathiran.onlinenursery.service;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the implementation for the Order Interface.
 *
 * Methods:
 * createOrder -  This method is used to create an Order for the user.
 ***************************/
import lombok.extern.slf4j.Slf4j;
import org.sivakamiveerapathiran.onlinenursery.models.*;
import org.sivakamiveerapathiran.onlinenursery.repository.OrderRepository;
import org.sivakamiveerapathiran.onlinenursery.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
@Slf4j
@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartListService cartListService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, User user) {

        log.info("Inside createOrder Method");

        Order order = new Order();
        order.setBillingAddress(billingAddress);
        order.setOrderStatus("created");
        order.setPayment(payment);
       order.setShippingAddress(shippingAddress);
        List<CartList> cartItemList = cartListService.findByShoppingCart(shoppingCart);

        for (CartList cartItem : cartItemList) {
            Product product = cartItem.getProduct();
            cartItem.setOrder(order);
            product.setQuantity(product.getQuantity() - cartItem.getAmount());
        }

        order.setCartItemList(cartItemList);
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderTotal(shoppingCart.getTotal());
        shippingAddress.setOrder(order);
        billingAddress.setOrder(order);
        payment.setOrder(order);
        order.setUser(user);
        order = orderRepository.save(order);

        if(order!=null){

            shoppingCart.setStatus("I");
            shoppingCartRepository.save(shoppingCart);
        }
        return order;
    }






}


