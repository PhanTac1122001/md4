package com.ra.base_project_md4.service.impl;


import com.ra.base_project_md4.model.dto.request.SizeRequest;
import com.ra.base_project_md4.model.dto.response.SizeResponse;
import com.ra.base_project_md4.model.entity.Size;
import com.ra.base_project_md4.repository.SizeRepository;
import com.ra.base_project_md4.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    @Override
    public Page<Size> findAll(String sizeName, Integer pageNo, Integer pageSize, String sortBy, String sortDirect) {
        Sort sort=Sort.by(sortBy);
        sort=sortDirect.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);

        return sizeRepository.findBySizeNameContaining(sizeName, pageable);
    }

    @Override
    public SizeResponse save(SizeRequest sizeRequest) {
        Size size=Size.builder()
                .sizeName(sizeRequest.getSizeName())
                .status(sizeRequest.getStatus())
                .build();
        Size sizeNew=sizeRepository.save(size);
        return SizeResponse.builder()
                .id(sizeNew.getId())
                .sizeName(sizeNew.getSizeName())
                .status(sizeNew.getStatus())
                .build();
    }

    @Override
    public Size findById(Long id) {
        return sizeRepository.findById(id).orElseThrow();
    }

    @Override
    public SizeResponse update(SizeRequest sizeRequest, Long sizeId) {
        Size size=findById(sizeId);
        size.setSizeName(sizeRequest.getSizeName());
        size.setStatus(sizeRequest.getStatus());
        Size sizeUpdate=sizeRepository.save(size);
        return SizeResponse.builder()
                .id(sizeUpdate.getId())
                .sizeName(sizeUpdate.getSizeName())
                .status(sizeUpdate.getStatus())
                .build();
    }

    @Override
    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }
}
