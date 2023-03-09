package org.sivakamiveerapathiran.onlinenursery.service;

import org.sivakamiveerapathiran.onlinenursery.models.RoleTable;

import java.util.List;

public interface RoleTableService {

    public void saveRole(RoleTable role);
    public RoleTable findRoleByRoleName(String name);
    public List<RoleTable> getAllRoles();
    public List<RoleTable> getRolesByUser(long id);
}