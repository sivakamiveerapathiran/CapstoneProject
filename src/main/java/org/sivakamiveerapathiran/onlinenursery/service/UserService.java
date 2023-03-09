package org.sivakamiveerapathiran.onlinenursery.service;

import org.sivakamiveerapathiran.onlinenursery.models.ShoppingCart;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User createUser(User user);
    //User validateStudent(String sEmail,String Pass);
    public User findByEmail(String email);

    public ShoppingCart findByUserId(User user);

    public UserDetails loadUserByUsername(String userName);


}
