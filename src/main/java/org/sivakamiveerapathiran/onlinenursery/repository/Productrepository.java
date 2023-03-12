package org.sivakamiveerapathiran.onlinenursery.repository;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the Repository for the Product entity.
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Productrepository extends JpaRepository<Product, Integer> {
    public Product findProductById(int productid);
}
