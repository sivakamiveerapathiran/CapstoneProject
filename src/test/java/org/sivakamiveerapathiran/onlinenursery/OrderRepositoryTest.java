package org.sivakamiveerapathiran.onlinenursery;

import com.example.firsdemo.demoFirst.models.*;
import org.sivakamiveerapathiran.onlinenursery.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sivakamiveerapathiran.onlinenursery.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void findById() {

        //findFirst().orElse(null)
        Order order=orderRepository.findById(1);
        Assertions.assertEquals("created",order.getOrderStatus());

    }

}
