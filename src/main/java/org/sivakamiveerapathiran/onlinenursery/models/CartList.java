package org.sivakamiveerapathiran.onlinenursery.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CartList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    private int subtotal;
    @OneToOne
    private Product product;



    @OneToMany(mappedBy = "cartItem")
    private List<ProductToCart> productToCart;

    @ManyToOne
    @JoinColumn(name = "shopcart_id")
    private ShoppingCart shoppingCart;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public CartList() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
    public List<ProductToCart> getProductToCart() {
        return productToCart;
    }

    public void setProductToCart(List<ProductToCart> productToCart) {
        this.productToCart = productToCart;
    }

}