package com.ra.base_project_md4.controller.admin;

import com.ra.base_project_md4.model.dto.request.ColorRequest;
import com.ra.base_project_md4.model.dto.response.ColorResponse;
import com.ra.base_project_md4.model.entity.Color;
import com.ra.base_project_md4.service.ColorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/color")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirect,
            @RequestParam(defaultValue = "", required = false) String colorName
    ) {
        Page<Color> colors = colorService.findAll(colorName, pageNo, pageSize, sortBy, sortDirect);
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getColorById(@PathVariable Long id) {

        return new ResponseEntity<>(colorService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ColorResponse> save(@Valid @RequestBody ColorRequest colorRequest) {
        ColorResponse colorResponse = colorService.save(colorRequest);
        return new ResponseEntity<>(colorResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ColorResponse> update(@PathVariable Long id,@RequestBody ColorRequest colorRequest){
        ColorResponse colorUpdate = colorService.update(colorRequest,id);
        return new ResponseEntity<>(colorUpdate,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        colorService.delete(id);
        return new ResponseEntity<>("Delete Successfully",HttpStatus.NO_CONTENT);
    }
}
