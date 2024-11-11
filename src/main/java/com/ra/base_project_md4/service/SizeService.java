package com.ra.base_project_md4.service;


import com.ra.base_project_md4.model.dto.request.SizeRequest;
import com.ra.base_project_md4.model.dto.response.SizeResponse;
import com.ra.base_project_md4.model.entity.Size;
import org.springframework.data.domain.Page;

public interface SizeService {
    Page<Size> findAll(String sizeName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    SizeResponse save(SizeRequest sizeRequest);

    SizeResponse update(SizeRequest sizeRequest,Long sizeId);

    Size findById(Long id);

    void delete(Long id);
}
