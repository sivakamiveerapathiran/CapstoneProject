package org.sivakamiveerapathiran.onlinenursery.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date orderDate;
    private Date shippingDate;
    private String shippingMethod;
    private String orderStatus;
    private  int orderTotal;

    public Order(Long id, Date orderDate, Date shippingDate, String shippingMethod, String orderStatus, int orderTotal, List<CartList> cartItemList, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, User user) {
        this.id = id;
        this.orderDate = orderDate;
        this.shippingDate = shippingDate;
        this.shippingMethod = shippingMethod;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.cartItemList = cartItemList;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.payment = payment;
        this.user = user;
    }

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL )
    private List<CartList> cartItemList;

    @OneToOne(cascade=CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @OneToOne(cascade=CascadeType.ALL)
    private BillingAddress billingAddress;

    @OneToOne(cascade=CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    private User user;


}
