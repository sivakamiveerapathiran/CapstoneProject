package org.sivakamiveerapathiran.onlinenursery.controller;


import org.sivakamiveerapathiran.onlinenursery.security.UserPrincipal;
import org.sivakamiveerapathiran.onlinenursery.service.CartListService;
import org.sivakamiveerapathiran.onlinenursery.service.ProductService;
import org.sivakamiveerapathiran.onlinenursery.service.ShopCartSvc;
import org.sivakamiveerapathiran.onlinenursery.service.UserService;
import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
public class ShoppingCartController {


    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopCartSvc shoppingCartService;
    @Autowired
    private CartListService cartListService;

    @Autowired
    public ShoppingCartController(ShopCartSvc shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }


    @PostMapping("/shoppingCart/addItemtoShoppingCart")
     public String addItemtoShoppingCart(Model model,
              @RequestParam("productid") int productid, @RequestParam("quantity") int RequiredQty){
        try{
            UserPrincipal usrprin=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("UserName: "+usrprin.getUsername());
            User user =userService.findByEmail(usrprin.getUsername());
         Product product=productService.findProductById(productid);
         CartList cartList = cartListService.addProductToCartList(product,user,RequiredQty);
        }
        catch(Exception e){
            e.printStackTrace();
            log.info(e.toString());

        }
         return "redirect:/product/listAllProducts";
    }

}

