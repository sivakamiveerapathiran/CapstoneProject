package org.sivakamiveerapathiran.onlinenursery.service;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the implementation for the Shopping Cart Interface.
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopCartSvcImpl implements ShopCartSvc {

    @Autowired
    private CartListService cartListService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


}
