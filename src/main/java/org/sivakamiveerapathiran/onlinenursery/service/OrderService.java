package org.sivakamiveerapathiran.onlinenursery.service;

import org.sivakamiveerapathiran.onlinenursery.models.*;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart,
                      ShippingAddress shippingAddress,
                      BillingAddress billingAddress,
                      Payment payment,
                      User user);

   /// public Order findById(int  id);
}
