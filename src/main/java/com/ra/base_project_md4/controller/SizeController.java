package com.ra.base_project_md4.controller;


import com.ra.base_project_md4.model.dto.request.SizeRequest;
import com.ra.base_project_md4.model.dto.response.SizeResponse;
import com.ra.base_project_md4.model.entity.Size;
import com.ra.base_project_md4.service.SizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/size")
@RequiredArgsConstructor
public class SizeController {
    private final SizeService sizeService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String sizeName
    ) {
        Page<Size> sizes = sizeService.findAll(sizeName, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(sizes.getContent(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSizeById(@PathVariable Long id) {
        Size size = sizeService.findById(id);
        if (size != null) {
            return new ResponseEntity<>(size, HttpStatus.OK);
        }
        Map<String, String> error = new HashMap<>();
        error.put("mess", "Not Found");
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<SizeResponse> save(@Valid @RequestBody SizeRequest sizeRequest) {
        SizeResponse sizeResponse = sizeService.save(sizeRequest);
        return new ResponseEntity<>(sizeResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SizeResponse> update(@PathVariable Long id,@RequestBody SizeRequest sizeRequest){
        SizeResponse categoryUpdate = sizeService.update(sizeRequest,id);
        return new ResponseEntity<>(categoryUpdate,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        sizeService.delete(id);
        return new ResponseEntity<>("Delete Successfully",HttpStatus.NO_CONTENT);
    }
}
