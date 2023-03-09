package org.sivakamiveerapathiran.onlinenursery.service;

import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public void createProduct(Product product, MultipartFile file) throws IOException;
    List<Product> getAllProducts();

     Product findProductById(int productid);

}
