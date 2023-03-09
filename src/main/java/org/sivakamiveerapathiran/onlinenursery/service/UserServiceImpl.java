package org.sivakamiveerapathiran.onlinenursery.service;

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
           // throw new RuntimeException("User already Exist");
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

        System.out.println("loadUserByUsername");
        User user = userrepo.findByEmail(userName);
        System.out.println("1:loadUserByUsername");
        if (user == null) {
            // log.warn("Invalid username or password {}", userName);

            throw new UsernameNotFoundException("Invalid username or password.");
        }
        System.out.println("2:loadUserByUsername");
       /* return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));*/
        return new UserPrincipal(user, roleTableService.getRolesByUser(user.getId()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleTable> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}



