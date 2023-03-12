package org.sivakamiveerapathiran.onlinenursery.service;
/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the interface for the Roletable entity.
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.models.RoleTable;

import java.util.List;

public interface RoleTableService {

    public void saveRole(RoleTable role);
    public RoleTable findRoleByRoleName(String name);
    public List<RoleTable> getAllRoles();
    public List<RoleTable> getRolesByUser(long id);
}