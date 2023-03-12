package org.sivakamiveerapathiran.onlinenursery.models;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the POJO for the Payment entity.
 ***************************/
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvc;
    private String holderName;
    @OneToOne
    private Order order;
}
