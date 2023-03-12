/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the controller methods for the Product Functionalities.
 * Methods:
 * createProduct - This Method is used to create a product in the Db with details from the Screen.
 * listProducts - This Method is used to list all the available products to the user.
 * listProductsPost - This Method is used to list all the available products to the user. This is launched form the login form
 * ProductDetailPage - This Method is used to display add a product to Cart page.
 * listProductsadmin - This Method is used to list the available products to the Admin
 * ProductDetailPageadmin - This Method is used to display the add a product to system admin page
 * NewProductDetailPageadmin - This Method is used to display the Update details to system page
 * UpdateProduct - This Method is used to update product details to the system
 * ProductDeleteAdminDetailPage - This Method is used to display the Delete product page to Admin
 * deleteProduct - This Method is used to delete a product from the system.
 ***************************/
package org.sivakamiveerapathiran.onlinenursery.controller;

import lombok.extern.slf4j.Slf4j;
import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.repository.Productrepository;
import org.sivakamiveerapathiran.onlinenursery.service.ProductService;
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
public class ProductController {
  private  ProductService productService;
    private final Productrepository productrepository;

    @GetMapping("/product/productDetails")
    public String registerForm(Model model) {
        model.addAttribute("product", new Product());
        return "Productdetails";
    }

    @Autowired
    public ProductController(ProductService productService,
                             Productrepository productrepository) {
        this.productService = productService;
        this.productrepository = productrepository;
    }

    @PostMapping("/product/addProduct")
    public String createProduct(@ModelAttribute("product") Product product,Model model,
                                        @RequestParam("ImageFile") MultipartFile file) throws IOException {
       productService.createProduct(product,file);
        return "redirect:/product/listAllProducts";
       // return ResponseEntity.status(HttpStatus.CREATED).body("Product created");
    }


    @GetMapping("/product/listAllProducts")
    public String listProducts(Model model){
        log.info("Inside listProducts Method");
        List<Product> dbproduct=productService.getAllProducts();
        List<Product> pgproduct=new ArrayList<>();
        for(Product product:dbproduct){
            if(product.getQuantity()>0){
                pgproduct.add(product);
            }
        }
        model.addAttribute("products",pgproduct);
        log.info("Exit listProducts Method");
        return "Listproducts";
    }

    @PostMapping("/product/loginListProducts")
    public String listProductsPost(Model model){
        log.info("Inside listProductsPost Method");
        List<Product> dbproduct=productService.getAllProducts();
        List<Product> pgproduct=new ArrayList<>();
        for(Product product:dbproduct){
            if(product.getQuantity()>0){
                pgproduct.add(product);
            }
        }
        model.addAttribute("products",pgproduct);
        log.info("Exit listProductsPost Method");
        return "Listproducts";
    }

   @GetMapping ("/product/addProductToCart/{productId}")
    public String ProductDetailPage(@PathVariable("productId") int productId,Model model) {
       log.info("Inside ProductDetailPage Method");
        Product product=productService.findProductById(productId);
         model.addAttribute("product",product);
       log.info("Loading  AddProductDetails Page");
        return "AddProductDetails";
    }


    @GetMapping("/product/adminUpdate")
    public String listProductsadmin(Model model){
        log.info("Inside listProductsadmin Method");
        model.addAttribute("product",productService.getAllProducts());
        log.info("Loading  AdminUpdate Page");
        return "AdminUpdate";
    }
    @GetMapping ("/product/addProductAdmin/{productId}")
    public String ProductDetailPageadmin(@PathVariable("productId") int productId,Model model) {
        log.info("Inside ProductDetailPageadmin Method");
        log.info("input productId: "+productId);
        Product product=productService.findProductById(productId);
        log.info("Product Object product name: "+product.getProductName());
        model.addAttribute("product",product);
        log.info("Loading  productDetailsAdmin Page");
        return "productDetailsAdmin";
    }


    @GetMapping ("/product/addNewProductAdmin")
    public String NewProductDetailPageadmin(Model model) {
        log.info("Inside NewProductDetailPageadmin Method");
        model.addAttribute("product",new Product());
        log.info("Loading  productDetailsAdmin Page");
        return "productDetailsAdmin";
    }
    @PostMapping("/product/addProductAdmin")
    public String UpdateProduct(@ModelAttribute("product") Product product,Model model,
                                        @RequestParam("ImageFile") MultipartFile file) throws IOException {
        log.info("Inside UpdateProduct Method");
        log.trace("Product Data: "+ product.getProductName());
        productService.createProduct(product,file);
        log.info("redirect to /product/listAllProducts");
        return "redirect:/product/listAllProducts";
    }
  @GetMapping ("/product/deleteProductToCart/{productId}")
    public String ProductDeleteAdminDetailPage(@PathVariable("productId") int productId,Model model) {
        log.info("Inside ProductAdminDetailPage Method");
        log.trace("input productId: "+productId);
        System.out.println("12312122:input productId: "+productId);
        Product product=productService.findProductById(productId);
        log.trace("Product Data: "+ product.getProductName());
        model.addAttribute("product",product);
        log.info("Loading  adminDeleteItem Page");
        return "adminDeleteItem";
    }

    @PostMapping("/product/deleteAdminProduct")
    public String deleteProduct(@ModelAttribute("product") Product product, Model model)
            throws IOException
    {
        log.info("Inside deleteCart Method");
        int localid= product.getId();
        Integer localchange =  localid ;
        Product productdb=productrepository.findProductById(localchange);
        log.trace("Product Data: "+ product.getProductName());
        productrepository.delete(productdb);
        log.info("redirect:/product/adminUpdate");
        return "redirect:/product/adminUpdate";
    }
}