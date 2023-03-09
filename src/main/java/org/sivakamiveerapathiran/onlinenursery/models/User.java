package org.sivakamiveerapathiran.onlinenursery.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="Register", uniqueConstraints = @UniqueConstraint(columnNames = "Email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String firstname;
    private String lastname;

    private String email;
    private String password;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String Zipcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ShoppingCart> shoppingCart;
    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection <RoleTable> roles;

    public User(Long id, String firstname, String lastname, String email, String password, String address1, String address2, String city, String state, String zipcode, List<ShoppingCart> shoppingCart, List<Order> orderList) {
        Id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        Zipcode = zipcode;
        this.shoppingCart = shoppingCart;
        this.orderList = orderList;
    }


}


