package org.sivakamiveerapathiran.onlinenursery.models;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the POJO for the ProductToCart entity.
 ***************************/
import jakarta.persistence.*;

@Entity
public class ProductToCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name="cartitem_id")
    private CartList cartItem;

    public ProductToCart() {
    }

    public ProductToCart(Long id, Product product, CartList cartItem) {
        this.id = id;
        this.product = product;
        this.cartItem = cartItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartList getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartList cartItem) {
        this.cartItem = cartItem;
    }
}
