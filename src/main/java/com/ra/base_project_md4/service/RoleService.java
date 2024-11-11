package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.constant.RoleName;
import com.ra.base_project_md4.model.entity.Role;

public interface RoleService {
    Role findRoleByRoleName(RoleName roleName);
}
