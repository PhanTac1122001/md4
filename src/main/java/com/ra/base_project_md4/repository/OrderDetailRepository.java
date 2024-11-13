package com.ra.base_project_md4.repository;

import com.ra.base_project_md4.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
