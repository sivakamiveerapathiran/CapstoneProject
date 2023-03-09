package org.sivakamiveerapathiran.onlinenursery.repository;
import org.sivakamiveerapathiran.onlinenursery.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository  extends JpaRepository<User,Long>{

   public User findByEmail(String email);


}
