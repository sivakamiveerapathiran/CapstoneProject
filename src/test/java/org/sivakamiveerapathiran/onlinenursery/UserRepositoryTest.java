package org.sivakamiveerapathiran.onlinenursery;

import org.sivakamiveerapathiran.onlinenursery.repository.Userrepository;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.sivakamiveerapathiran.onlinenursery.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private Userrepository userrepo;

//@Autowired
private UserServiceImpl userService;


    @Test
    public void createUser() {
        userService=new UserServiceImpl(userrepo);
        User user = new User();
        user.setFirstname("Raja");
        user.setLastname("Pandian");
        user.setEmail("Rajasai1@gmail.com");
        user.setPassword("sai");
        user.setAddress1("610");
        user.setAddress2("humming");
        user.setCity("celina");
        user.setState("Texas");
        User saveduser = userService.createUser(user);
        Assertions.assertEquals("Texas",user.getState());
    }

    @Test
    public void findByEmail() {

        User user = userrepo.findByEmail("RAJAPANDIAN.U@GMAIL.COM");
        Assertions.assertEquals(1, user.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = {"RAJAPANDIAN.U@GMAIL.COM", "Rajasai@gmail.com"})
    public void findByEmails(String strings) {
        User user = userrepo.findByEmail(strings);
        Assertions.assertEquals(1, user.getId());

    }
}