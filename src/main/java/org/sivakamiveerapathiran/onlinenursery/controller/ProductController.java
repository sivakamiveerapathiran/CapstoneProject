package org.sivakamiveerapathiran.onlinenursery.controller;

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

@Controller
public class ProductController {
  private  ProductService productService;

    @GetMapping("/product/productDetails")
    public String registerForm(Model model) {
        model.addAttribute("product", new Product());
        return "Productdetails";
    }

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/addProduct")
    //public ResponseEntity<?> createUser (@RequestBody ProductDto productdto )
    public String createUser(@ModelAttribute("product") Product product,Model model,
                                        @RequestParam("ImageFile") MultipartFile file) throws IOException {
       productService.createProduct(product,file);
        return "redirect:/product/listAllProducts";
       // return ResponseEntity.status(HttpStatus.CREATED).body("Product created");
    }


    @GetMapping("/product/listAllProducts")
    public String listProducts(Model model){
        List<Product> dbproduct=productService.getAllProducts();
        List<Product> pgproduct=new ArrayList<>();
        for(Product product:dbproduct){
            if(product.getQuantity()>0){
                pgproduct.add(product);
            }
        }
        model.addAttribute("products",pgproduct);
        return "Listproducts";
    }

    @PostMapping("/product/loginListProducts")
    public String listProductsPost(Model model){
        System.out.println("inside");
        List<Product> dbproduct=productService.getAllProducts();
        List<Product> pgproduct=new ArrayList<>();
        for(Product product:dbproduct){
            if(product.getQuantity()>0){
                pgproduct.add(product);
            }
        }
        model.addAttribute("products",pgproduct);
        return "Listproducts";
    }

   @GetMapping ("/product/addProductToCart/{productId}")
    public String ProductDetailPage(@PathVariable("productId") int productId,Model model) {
        System.out.println("12312122:input productId: "+productId);
        Product product=productService.findProductById(productId);
        //System.out.println("12312122:product: "+product.getProductname());
        model.addAttribute("product",product);
        return "AddProductDetails";
    }


    @GetMapping("/product/adminUpdate")
    public String listProductsadmin(Model model){
        model.addAttribute("product",productService.getAllProducts());
        return "AdminUpdate";
    }
    @GetMapping ("/product/addProductAdmin/{productId}")
    public String ProductDetailPageadmin(@PathVariable("productId") int productId,Model model) {
        System.out.println("12312122:input productId: "+productId);
        Product product=productService.findProductById(productId);
        //System.out.println("12312122:product: "+product.getProductname());
        model.addAttribute("product",product);
        return "productDetailsAdmin";
    }

    @GetMapping ("/product/addNewProductAdmin")
    public String NewProductDetailPageadmin(Model model) {
        model.addAttribute("product",new Product());
        return "productDetailsAdmin";
    }
    @PostMapping("/product/addProductAdmin")
    //public ResponseEntity<?> createUser (@RequestBody ProductDto productdto )
    public String UpdateProduct(@ModelAttribute("product") Product product,Model model,
                                        @RequestParam("ImageFile") MultipartFile file) throws IOException {
        productService.createProduct(product,file);
        return "redirect:/product/listAllProducts";
        //return ResponseEntity.status(HttpStatus.CREATED).body("Product Updated");
    }

}
