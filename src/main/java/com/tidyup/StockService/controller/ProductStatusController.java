package com.tidyup.StockService.controller;

import com.tidyup.StockService.Service.ProductStatusService;
import com.tidyup.StockService.domain.product.dto.DetailedProductStatusDTO;
import com.tidyup.StockService.domain.product.dto.ProductStatusDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/status")
public class ProductStatusController {

    private final String BASE_URI = "http://localhost::8080/status";

    @Autowired
    private ProductStatusService productStatusService;

    @PostMapping
    public ResponseEntity<DetailedProductStatusDTO> createProductStatus(@RequestBody @Valid ProductStatusDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        DetailedProductStatusDTO createProductStatus = productStatusService.create(dto);
        URI location = uriComponentsBuilder.path(BASE_URI + "/{id}").buildAndExpand(createProductStatus.id()).toUri();
        return ResponseEntity.created(location).body(createProductStatus);
    }

    @GetMapping
    public ResponseEntity<Page<DetailedProductStatusDTO>> getAllProductStatus(@PageableDefault(size = 10) Pageable pageable) {
        Page<DetailedProductStatusDTO> page = productStatusService.getAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedProductStatusDTO> getAllProductStatus(@PathVariable Long id) {
        DetailedProductStatusDTO productStatusDTO = productStatusService.getById(id);
        return ResponseEntity.ok(productStatusDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedProductStatusDTO> updateProductStatus(@PathVariable Long id, @RequestBody @Valid ProductStatusDTO dto) {
        DetailedProductStatusDTO productStatusDTO = productStatusService.update(id, dto);
        return ResponseEntity.ok(productStatusDTO);
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteProductStatus(@PathVariable Long id) {
        productStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
