package com.ra.base_project_md4.service.impl;

import com.ra.base_project_md4.model.dto.request.ColorRequest;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.repository.ColorRepository;
import com.ra.base_project_md4.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    @Override
    public Page<Color> findAll(String colorName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=Sort.by(sortBy);
        sort=sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        return colorRepository.findByColorNameContaining(colorName, pageable);
    }

    @Override
    public ColorResponse save(ColorRequest colorRequest) {
        Color color=Color.builder()
                .colorName(colorRequest.getColorName())
                .colorCode(colorRequest.getColorCode())
                .status(colorRequest.getStatus())
                .build();
        Color colorNew=colorRepository.save(color);
        return ColorResponse.builder()
                .id(colorNew.getId())
                .colorCode(colorNew.getColorCode())
                .colorName(colorNew.getColorName())
                .status(colorNew.getStatus())
                .build();
    }

    @Override
    public ColorResponse update(ColorRequest colorRequest, Long colorId) {
        Color color=findById(colorId);
        color.setColorCode(colorRequest.getColorCode());
        color.setColorName(colorRequest.getColorName());
        color.setStatus(colorRequest.getStatus());
        Color colorUpdate=colorRepository.save(color);
        return ColorResponse.builder()
                .id(colorUpdate.getId())
                .colorName(colorUpdate.getColorName())
                .colorCode(colorUpdate.getColorCode())
                .status(colorUpdate.getStatus())
                .build();
    }

    @Override
    public Color findById(Long id) {
        return colorRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        colorRepository.deleteById(id);
    }
}
