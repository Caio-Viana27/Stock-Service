package com.tidyup.StockService.controller;

import com.tidyup.StockService.Service.ProductService;
import com.tidyup.StockService.domain.product.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final String URI_BASE = "https://localhost:8080/products";

    @Autowired
    private ProductService productService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailedProductDTO> createProduct(@RequestBody @Valid CreateProductRequest productData, UriComponentsBuilder uriBuilder) {
        DetailedProductDTO product = productService.createProduct(productData);
        URI uri = uriBuilder.path(URI_BASE + "/{id}").buildAndExpand(product.Id()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<SimpleProductDTO>> getProducts(@PageableDefault(size = 20) Pageable pageable) {
        Page<SimpleProductDTO> page = productService.getProducts(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleProductDTO> getProduct(@PathVariable UUID id) {
        SimpleProductDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailedProductDTO> updateProduct(@PathVariable UUID id, @RequestBody @Valid UpdateProductRequest data) {
        DetailedProductDTO product = productService.update(id, data);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DetailedProductDTO> updateProductStatus(@PathVariable UUID id, @RequestBody @Valid ProductStatusDTO data) {
        DetailedProductDTO product = productService.update(id, data);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{id}/categories")
    public ResponseEntity<DetailedProductDTO> updateProductStatus(@PathVariable UUID id, @RequestBody @Valid List<CategoryDTO> data) {
        DetailedProductDTO product = productService.update(id, data);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
