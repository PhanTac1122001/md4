package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.constant.RoleName;
import com.ra.base_project_md4.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(RoleName roleName);
}
