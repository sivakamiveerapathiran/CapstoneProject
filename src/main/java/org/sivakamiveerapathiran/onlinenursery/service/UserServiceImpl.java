package org.sivakamiveerapathiran.onlinenursery.service;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the implementation for the User table Interface.
 * Methods:
 * createUser - This method is used to create an user from the data from the screen.
 * findByUserId - This method is used to find a shopping cart by user id. If not found, it creates a new shopping cart for the user.
 * inquireByUserId - This method is used to find a shopping cart by user id.
 * loadUserByUsername - This method is used to create a new UserPrincipal object by the user ID
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.exception.CustomerAlreadyExistsException;
import org.sivakamiveerapathiran.onlinenursery.models.RoleTable;
import org.sivakamiveerapathiran.onlinenursery.repository.ShoppingCartRepository;
import org.sivakamiveerapathiran.onlinenursery.repository.Userrepository;
import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.sivakamiveerapathiran.onlinenursery.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Service
public class UserServiceImpl implements UserService {

    private Userrepository userrepo;


    @Autowired
    public RoleTableService roleTableService;

    private BCryptPasswordEncoder BcryptEncoder;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public UserServiceImpl(Userrepository userrepo) {
        this.userrepo = userrepo;
    }

    @Override
    public User createUser(User user) {
        User retuser;
        String emailstr=user.getEmail();
        User checkuser=userrepo.findByEmail(emailstr);
        if (checkuser== null){
            BCryptPasswordEncoder bcryptEncoder=new BCryptPasswordEncoder(11);
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(roleTableService.findRoleByRoleName("ROLE_USER")));
            retuser =userrepo.save(user);}
        else {
            throw new CustomerAlreadyExistsException(
                    "Customer already exists!!");
        }

        return retuser;
    }


    public User findByEmail(String email) {
        User user= userrepo.findByEmail(email);

        return user;
    }

    @Override
    public ShoppingCart findByUserId(User user){
        Long userid=user.getId();
        ShoppingCart shopcart;
        List<ShoppingCart> shopcartlist=shoppingCartRepository.findByUserID(userid);
        if(shopcartlist.isEmpty()){
            shopcart=new ShoppingCart();
            shopcart.setUser(user);
            shopcart.setStatus("A");
            shopcart= shoppingCartRepository.save(shopcart);

        }
        else {
            shopcart=shopcartlist.get(0);
        }
        return shopcart;
    }

    public ShoppingCart inquireByUserId(User user){
        Long userid=user.getId();
        ShoppingCart shopcart;
        List<ShoppingCart> shopcartlist=shoppingCartRepository.findByUserID(userid);
        if(shopcartlist.isEmpty()){
          return null;

        }
        else {
            shopcart=shopcartlist.get(0);
        }
        return shopcart;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userrepo.findByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new UserPrincipal(user, roleTableService.getRolesByUser(user.getId()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleTable> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}



