package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.constant.RoleName;
import com.ra.base_project_md4.model.entity.Role;
import com.ra.base_project_md4.repository.RoleRepository;
import com.ra.base_project_md4.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findRoleByRoleName(RoleName roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
