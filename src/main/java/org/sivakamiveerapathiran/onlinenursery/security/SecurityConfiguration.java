package org.sivakamiveerapathiran.onlinenursery.security;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the Security filterchain  for the Project.
 *
 * Methods:
 * filterChain - used to build the filterchain for the login project
 ***************************/

import org.sivakamiveerapathiran.onlinenursery.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.Serializable;


@Configuration
public class SecurityConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Autowired
    private UserServiceImpl userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }

    //beans
    //bcrypt bean definition
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/", "/login*", "/images/*","/css/*", "/js/*", "/sign-up", "/user/*","/user/addUser")
                .permitAll()
                .requestMatchers("/cartList/ListCartproducts",
                        "/additemtoshoppingcart",
                        "Shoppingcart",
                        "/cartList/shoppingCartUpdate/*","/cartList/updateCartList/",
                        "/cartList/shoppingCartDelete/","/cartList/deleteCartList",
                        "/shoppingCart/addItemtoShoppingCart","/order/*","/product/addProductToCart/",
                        "/product/loginListProducts")
                .hasAnyRole("USER","ADMIN")
                //.anyRequest().authenticated()
                .requestMatchers("/product/adminUpdate","/product/addProductAdmin/*",
                        "/product/addProductAdmin/","/product/addProduct/*",
                        "/product/addNewProductAdmin/",
                        "/product/deleteProductToCart/",
                        "adminDeleteItem")
                .hasAnyRole("ADMIN")
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/home")
                .successForwardUrl("/product/loginListProducts")
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").permitAll();
System.out.println(http.toString());
        return http.build();

    }
}