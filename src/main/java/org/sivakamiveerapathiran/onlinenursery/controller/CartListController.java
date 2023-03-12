/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the controller
 * methods for the Cartlist.
 * These are mainly invoked form the Shopping Cart page in the application.
 * Methods:
 * listProducts - This method is used to list the products available in the Users Shopping Cart
 * shoppingCartUpdate - This method is used to render the Update an item in the user shopping Cart
 * updateCart -  This method is used to update an item in the user shopping Cart
 * productDetailDelete - This method is used to render the Delete an item page for the user shopping Cart
 * deleteCart -  This method is used to delete an item in the user shopping Cart
 ***************************/

package org.sivakamiveerapathiran.onlinenursery.controller;

import lombok.extern.slf4j.Slf4j;
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.sivakamiveerapathiran.onlinenursery.repository.CartListRepository;
import org.sivakamiveerapathiran.onlinenursery.security.UserPrincipal;
import org.sivakamiveerapathiran.onlinenursery.service.CartListService;
import org.sivakamiveerapathiran.onlinenursery.service.UserService;
import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class CartListController {

    CartListService cartListService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartListRepository cartListRepo;

    @Autowired
    public CartListController(CartListService cartListService) {
        this.cartListService = cartListService;
    }
    @GetMapping("/cartList/ListCartproducts")
    //public ResponseEntity<?>  listProducts(Model model){
    public String listProducts(Model model){

        log.info("Inside listProducts Method");
        UserPrincipal usrprin=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.debug("UserPrincipal: "+usrprin.getUsername());
        User user =userService.findByEmail(usrprin.getUsername());
        ShoppingCart shoppingCart=userService.findByUserId(user);
        log.debug("shoppingCart: "+ shoppingCart.getId());
        System.out.println(shoppingCart.getId());
        List<CartList> res=cartListService.getAllProducts(shoppingCart);
        System.out.println("res: "+res);

        model.addAttribute("cartlistresult",res);
        log.info("Loading  Shoppingcart Page");
       return "Shoppingcart";
    }

    @GetMapping ("/cartList/shoppingCartUpdate/{CartListID}")
    public String shoppingCartUpdate(@PathVariable("CartListID") int cartListid, Model model){
        log.info("Inside shoppingCartUpdate Method");
        CartList cartList = cartListService.findById(cartListid);
        Product product = cartList.getProduct();
        model.addAttribute("product", product);
        model.addAttribute("cartlist",cartList);
        log.debug("shoppingCart: "+ product.getProductName());
        log.info("Loading ShoppingcartUpdate Page");
        return "ShoppingcartUpdate";

    }

    @PostMapping("/cartList/updateCartList")
    public String updateCart(@ModelAttribute("product") Product product,
                                        Model model,
                                        @ModelAttribute("cartlist") CartList cartListfrompage)
                                         throws IOException
    {
        log.info("Inside updateCart Method");
    Long localid= cartListfrompage.getId();
        log.debug("localid: "+localid);
        Integer localchange = (int) (long) localid ;
        CartList cartlistdb=cartListService.findById(localchange);
        Product productdb=cartlistdb.getProduct();
        int getcurrentquantity=cartListfrompage.getAmount();
        int newPrice=getcurrentquantity*productdb.getPrice();
        cartlistdb.setAmount(getcurrentquantity);
        cartlistdb.setSubtotal(newPrice);

        cartListRepo.save(cartlistdb);
        log.info("redirect:/cartList/ListCartproducts");
        return "redirect:/cartList/ListCartproducts";
       // return ResponseEntity.status(HttpStatus.CREATED).body("Cart Updated");
    }
    @GetMapping ("/cartList/shoppingCartDelete/{CartListID}")
    public String productDetailDelete(@PathVariable("CartListID") int cartListid, Model model){
        log.info("Inside productDetailDelete Method");
        CartList cartList = cartListService.findById(cartListid);
        Product product = cartList.getProduct();
        model.addAttribute("product", product);
        model.addAttribute("cartlist",cartList);
        log.info("Inside ShoppingDeleteCart Method");
        return "ShoppingDeleteCart";

    }
    @PostMapping("/cartList/deleteCartList")
    public String deleteCart(@ModelAttribute("product") Product product, Model model,@ModelAttribute("cartlist") CartList cartListfrompage)
            throws IOException
    {
        log.info("Inside deleteCart Method");
        Long localid= cartListfrompage.getId();
        Integer localchange = (int) (long) localid ;
        CartList cartlistdb=cartListService.findById(localchange);
        cartListRepo.delete(cartlistdb);
        log.info("redirect:/cartList/ListCartproducts");
        return "redirect:/cartList/ListCartproducts";
        //return ResponseEntity.status(HttpStatus.CREATED).body("Item Deleted");
    }
}



