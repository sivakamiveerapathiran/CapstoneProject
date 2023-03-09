package org.sivakamiveerapathiran.onlinenursery;

import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.sivakamiveerapathiran.onlinenursery.repository.Productrepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private Productrepository productrepository;
    @Test
    public void  findProductById(){
        Product product= productrepository.findProductById(102);
        Assertions.assertEquals(50,product.getPrice());

    }
    @Test
    public void getAllProducts(){
        List<Product> products=productrepository.findAll();
      //  System.out.println("Output"+products.get(2).getProductName());
        Assertions.assertEquals("li",products.get(2).getProductName());
    }
}
