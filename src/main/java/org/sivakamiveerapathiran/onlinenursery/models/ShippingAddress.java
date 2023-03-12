package org.sivakamiveerapathiran.onlinenursery.models;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the POJO for the ShippingAddress entity.
 ***************************/
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="ShipAddress")
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shippingName;
    private String shippingAddress1;
    private String shippingAddress2;

    private String shippingCity;
    private String shippingState;
    private String shippingZipcode;
    @OneToOne
    private Order order;

    public ShippingAddress(Long id, String shippingName, String shippingAddress1, String shippingAddress2, String shippingCity, String shippingState, String shippingZipcode, Order order) {
        this.id = id;
        this.shippingName = shippingName;
        this.shippingAddress1 = shippingAddress1;
        this.shippingAddress2 = shippingAddress2;
        this.shippingCity = shippingCity;
        this.shippingState = shippingState;
        this.shippingZipcode = shippingZipcode;
        this.order = order;
    }
}
