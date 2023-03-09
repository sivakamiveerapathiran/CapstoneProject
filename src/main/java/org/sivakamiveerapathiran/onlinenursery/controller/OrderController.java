package org.sivakamiveerapathiran.onlinenursery.controller;

import org.sivakamiveerapathiran.onlinenursery.models.*;
import org.sivakamiveerapathiran.onlinenursery.security.UserPrincipal;
import org.sivakamiveerapathiran.onlinenursery.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private UserService userService;
     @Autowired
    private ShopCartSvc shoppingCartService;
    @Autowired
    private CartListService cartListService;

    @Autowired
    private OrderServiceImpl orderserviceimpl;
    @Autowired

    public OrderController(UserService userService, ProductService productService, ShopCartSvc shoppingCartService, CartListService cartListService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.cartListService = cartListService;
    }
   @GetMapping("/order/checkOutPage")
    //public ResponseEntity<?>  listProducts(Model model){
    public String listProducts(Model model){
       // model.addAttribute("product",cartListService.getAllProducts());
       UserPrincipal usrprin=(UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       System.out.println("UserName: "+usrprin.getUsername());
       User user =userService.findByEmail(usrprin.getUsername());

        ShoppingCart shoppingCart=userService.findByUserId(user);
        List<CartList> res=cartListService.getAllProducts(shoppingCart);

        for(CartList resitem:res){
            System.out.println(resitem.getProduct().getProductName());
            System.out.println(resitem.getShoppingCart().getId());

        }
        model.addAttribute("cartlistresult",res);
       model.addAttribute("billingaddress", new BillingAddress());
       model.addAttribute("shippingaddress", new ShippingAddress());
       model.addAttribute("payment", new Payment());

       return "OrderList";
    }

    @PostMapping("/order/confirmOrderList")
    public String CreateOrder(@ModelAttribute("shippingaddress") ShippingAddress shippingaddress,
                                         @ModelAttribute("billingaddress") BillingAddress billingaddress,
                                         @ModelAttribute("payment") Payment payment)
                   throws IOException {

        System.out.println("billingaddress: "+billingaddress.toString());

        System.out.println("shippingaddress: "+shippingaddress.toString());
        UserPrincipal usrprin=(UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("UserName: "+usrprin.getUsername());
        User user =userService.findByEmail(usrprin.getUsername());
         ShoppingCart shoppingCart=userService.findByUserId(user);

        Order order=  orderserviceimpl.createOrder(shoppingCart,shippingaddress,billingaddress,payment,user);


        //  public Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, User user) {

        return "redirect:/product/listAllProducts";
           // return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
    }
    }


