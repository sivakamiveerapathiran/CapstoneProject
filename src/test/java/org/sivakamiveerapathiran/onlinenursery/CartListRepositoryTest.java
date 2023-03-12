package org.sivakamiveerapathiran.onlinenursery;

import org.sivakamiveerapathiran.onlinenursery.models.CartList;
import org.sivakamiveerapathiran.onlinenursery.repository.CartListRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class CartListRepositoryTest {
    @Autowired
    CartListRepository cartListRepository;
    @Test
    public void findById() {

        CartList cartList = cartListRepository.findById(52);
        Assertions.assertEquals(17,cartList.getAmount());

    }
@Test
    public void getAllProducts(){

        List<CartList> cartLists=cartListRepository.findAll();

      System.out.println("Size"+cartLists.size());
      Assertions.assertEquals(13,cartLists.size());
}
}