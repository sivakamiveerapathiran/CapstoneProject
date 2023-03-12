package org.sivakamiveerapathiran.onlinenursery.service;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the interface for the Order entity.
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.models.*;

public interface OrderService {
    Order createOrder(ShoppingCart shoppingCart,
                      ShippingAddress shippingAddress,
                      BillingAddress billingAddress,
                      Payment payment,
                      User user);

   /// public Order findById(int  id);
}
