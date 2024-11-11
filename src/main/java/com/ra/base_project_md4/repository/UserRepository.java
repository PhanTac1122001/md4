package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByUsername(String username);

    boolean existsByUsername(String username);

    Page<User> findByUsernameContaining(String username, Pageable pageable);
}
