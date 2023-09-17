package com.dlebre.SecurityDemo.services;

import com.dlebre.SecurityDemo.models.Role;
import com.dlebre.SecurityDemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(String roleName){
        return this.roleRepository.findByRoleName(roleName);
    }

    public void saveRole(Role roleName){
        this.roleRepository.save(roleName);
    }


}
