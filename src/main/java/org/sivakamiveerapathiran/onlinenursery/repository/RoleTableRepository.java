package org.sivakamiveerapathiran.onlinenursery.repository;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the Repository for the RoleTable entity.
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.models.RoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleTableRepository extends JpaRepository<RoleTable, Long> {
    public RoleTable findRoleByName(String role);

    @Query(value = "select * from role_table where role_table.id in (select role_id from users_roles where user_id = :id)", nativeQuery = true)
    public List<RoleTable> findRoleByUser(@Param("id") long id);
}
