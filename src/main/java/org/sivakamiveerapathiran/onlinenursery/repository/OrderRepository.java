package org.sivakamiveerapathiran.onlinenursery.repository;

import org.sivakamiveerapathiran.onlinenursery.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <Order,Integer> {
   Order findById(int id);

}