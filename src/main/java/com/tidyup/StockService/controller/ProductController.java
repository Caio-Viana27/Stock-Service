package com.tidyup.StockService.controller;

import com.tidyup.StockService.Repository.ProductRepository;
import com.tidyup.StockService.Service.ProductService;
import com.tidyup.StockService.domain.product.dto.DetailedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody DetailedProduct product) {
        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@RequestParam String id) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@RequestParam String id) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@RequestParam String id) {
        return ResponseEntity.ok(null);
    }
}
