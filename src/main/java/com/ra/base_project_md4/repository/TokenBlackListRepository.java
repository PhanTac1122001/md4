package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList,Long> {

        boolean existsByToken(String token);

}
