package org.sivakamiveerapathiran.onlinenursery.service;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the implementation for the Product Interface.
 *
 * Methods:
 * createProduct -  This method is used to create an Product in the system.
 * findProductById - This method is used to find a product by Product ID
 ***************************/
import lombok.extern.slf4j.Slf4j;
import org.sivakamiveerapathiran.onlinenursery.repository.Productrepository;
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
    public class ProductServiceImpl implements ProductService {


        private Productrepository productrepo;

        @Autowired
        public ProductServiceImpl(Productrepository productrepo){

            this.productrepo=productrepo;
        }

        @Override
        public void createProduct(Product product, MultipartFile file) throws IOException {
            log.info("Inside createProduct Method");
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            log.debug("fileName: "+fileName);
            if(!fileName.isBlank()) {
                Path uploadPath = Paths.get("src/main/upload/static/images");
                log.debug("uploadPath: "+uploadPath.toString());
    if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
    }
    try (InputStream inputStream = file.getInputStream()) {
        Path filePath = uploadPath.resolve(fileName);
        log.debug("filePath: "+filePath.toString());
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioe) {
        System.out.println(ioe.toString());
        log.error("ioe.toString(): "+ioe.toString());
        throw new IOException("Could not save image file: " + fileName, ioe);
    }
         product.setImageFileName(fileName);
            }
            else {
                Product productexist=productrepo.findProductById(product.getId());
                System.out.println(productexist.getImage());
          product.setImageFileName(productexist.getImage());
                }
    productrepo.save(product);
 }

    @Override
    public List<Product> getAllProducts() {

return productrepo.findAll();
    }

    @Override
    public Product findProductById(int productid) {
        log.info("Inside findProductById Method");
      Optional<Product> productopt =productrepo.findById(productid);
        Product product=   productopt.orElse(null);
        log.info("product: "+product.getProductName());
        return product;
    }

}

