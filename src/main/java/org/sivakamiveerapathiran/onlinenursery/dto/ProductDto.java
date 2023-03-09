package org.sivakamiveerapathiran.onlinenursery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDto {
    private String Productname;
    private String Productdesc;
    private int Quantity;
    private int Price;
      private String ImageFile;
}
