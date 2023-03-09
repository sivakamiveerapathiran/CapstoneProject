package org.sivakamiveerapathiran.onlinenursery.models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="Productdetails")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;
    private String productDesc;
    private int quantity;
    private int price;

    private String imageFileName;


    @OneToMany(mappedBy = "product")
    private List<ProductToCart> productToCart;


    public Product(int id, String productName, String productDesc, int quantity, int price, String imageFileName, List<ProductToCart> productToCart) {
        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.quantity = quantity;
        this.price = price;
        this.imageFileName = imageFileName;
        this.productToCart = productToCart;
    }
    public String getImageFileName() {
       return "/images/"+imageFileName;
       // return imageFileName;
        //return "static/Images/" +ImageFileName;
        // return ImageFileName;
    }
public String getImage(){
    return imageFileName    ;
}
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}

