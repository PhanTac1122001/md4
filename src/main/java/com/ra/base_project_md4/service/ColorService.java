package com.ra.base_project_md4.service;

import com.ra.base_project_md4.model.dto.request.ColorRequest;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.entity.Color;
import org.springframework.data.domain.Page;

public interface ColorService {
    Page<Color> findAll(String colorName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect);

    ColorResponse save(ColorRequest colorRequest);

    ColorResponse update(ColorRequest colorRequest,Long colorId);

    Color findById(Long id);

    void delete(Long id);
}
