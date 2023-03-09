package org.sivakamiveerapathiran.onlinenursery.controller;

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
       // model.addAttribute("product",cartListService.getAllProducts());
       // User user =userService.findByEmail("RAJAPANDIAN.U@GMAIL.COM");
        UserPrincipal usrprin=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("UserName: "+usrprin.getUsername());
        User user =userService.findByEmail(usrprin.getUsername());

        ShoppingCart shoppingCart=userService.findByUserId(user);
        System.out.println(shoppingCart.getId());
        List<CartList> res=cartListService.getAllProducts(shoppingCart);
        System.out.println("res: "+res);
        for(CartList resitem:res){
            System.out.println(resitem.getProduct().getProductName());
            System.out.println(resitem.getShoppingCart().getId());
        }
        model.addAttribute("cartlistresult",res);
      // return ResponseEntity.status(HttpStatus.CREATED).body("Product created");
       return "Shoppingcart";
    }

    @GetMapping ("/cartList/shoppingCartUpdate/{CartListID}")
    public String ProductDetailPageadmin(@PathVariable("CartListID") int cartListid, Model model){
        CartList cartList = cartListService.findById(cartListid);
        Product product = cartList.getProduct();
        model.addAttribute("product", product);
        model.addAttribute("cartlist",cartList);
        return "ShoppingcartUpdate";

    }

    @PostMapping("/cartList/updateCartList")
    public String updateCart(@ModelAttribute("product") Product product,
                                        Model model,
                                        @ModelAttribute("cartlist") CartList cartListfrompage)
                                         throws IOException
    {

    Long localid= cartListfrompage.getId();
        Integer localchange = (int) (long) localid ;
        CartList cartlistdb=cartListService.findById(localchange);
        int getcurrentquantity=cartListfrompage.getAmount();
        cartlistdb.setAmount(getcurrentquantity);
       // cartListRepo.delete(cartlistdb);
        cartListRepo.save(cartlistdb);
        return "redirect:/cartList/ListCartproducts";
       // return ResponseEntity.status(HttpStatus.CREATED).body("Cart Updated");
    }
    @GetMapping ("/cartList/shoppingCartDelete/{CartListID}")
    public String productDetailDelete(@PathVariable("CartListID") int cartListid, Model model){
        CartList cartList = cartListService.findById(cartListid);
        Product product = cartList.getProduct();
        model.addAttribute("product", product);
        model.addAttribute("cartlist",cartList);
        return "ShoppingDeleteCart";

    }
    @PostMapping("/cartList/deleteCartList")
    public String deleteCart(@ModelAttribute("product") Product product, Model model,@ModelAttribute("cartlist") CartList cartListfrompage)
            throws IOException
    {

        Long localid= cartListfrompage.getId();
        Integer localchange = (int) (long) localid ;
        CartList cartlistdb=cartListService.findById(localchange);
        cartListRepo.delete(cartlistdb);
        return "redirect:/cartList/ListCartproducts";
        //return ResponseEntity.status(HttpStatus.CREATED).body("Item Deleted");
    }
}

