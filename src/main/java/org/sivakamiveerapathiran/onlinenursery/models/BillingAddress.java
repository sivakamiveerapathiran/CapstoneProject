/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the POJO for the BillingAddress entity.
 ***************************/

package org.sivakamiveerapathiran.onlinenursery.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="Billaddress")
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address1;
    private String address2;

    private String city;
    private String state;
    private String zipcode;

    @OneToOne
    private Order order;

    public BillingAddress(Long id, String name, String address1, String address2, String city, String state, String zipcode, Order order) {
        this.id = id;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.order = order;
    }
}

