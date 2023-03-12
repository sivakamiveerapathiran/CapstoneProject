package org.sivakamiveerapathiran.onlinenursery.service;

/***************************
 * Author: Sivakami Veerapathiran
 * Description: This Class contains the implementation for the RoleTable Interface.
 ***************************/
import org.sivakamiveerapathiran.onlinenursery.repository.RoleTableRepository;
import org.sivakamiveerapathiran.onlinenursery.models.RoleTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RoleTableServiceImpl  implements RoleTableService{


    private RoleTableRepository roletblRepository;

    @Autowired
    public void RoleServiceImpl(RoleTableRepository roletblRepository) {
        this.roletblRepository = roletblRepository;
    }

    @Override
    @Transactional
    public void saveRole(RoleTable role) {
        roletblRepository.save(role);
    }

    @Override
    @Transactional
    public RoleTable findRoleByRoleName(String name) {
        return roletblRepository.findRoleByName(name);
    }

    @Override
    public List<RoleTable> getAllRoles() {
        return (List<RoleTable>) roletblRepository.findAll();
    }

    @Override
    public List<RoleTable> getRolesByUser(long id) {
        return roletblRepository.findRoleByUser(id);
    }
}

