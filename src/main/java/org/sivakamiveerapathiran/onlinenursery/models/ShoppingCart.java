package org.sivakamiveerapathiran.onlinenursery.models;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the POJO for the ShoppingCart entity.
 ***************************/
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Shoppingcart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int total;
    private String status;
    @OneToMany(mappedBy="shoppingCart", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<CartList> cartItemList;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        total = total;
    }

    public List<CartList> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartList> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
