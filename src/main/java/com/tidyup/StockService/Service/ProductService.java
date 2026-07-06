package com.tidyup.StockService.Service;

import com.tidyup.StockService.Repository.ProductRepository;
import com.tidyup.StockService.domain.product.dto.*;
import com.tidyup.StockService.domain.product.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public DetailedProductDTO createProduct(CreateProductRequest dto) {
        var product = new Product(dto);
        productRepository.save(product);
        return new DetailedProductDTO(product);
    }

    public Page<SimpleProductDTO> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(SimpleProductDTO::new);
    }

    public SimpleProductDTO getProduct(UUID id) {
        return productRepository.findById(id.toString()).map(SimpleProductDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public DetailedProductDTO update(UUID id, UpdateProductRequest data) {
        Product product = productRepository.findById(id.toString()).orElseThrow(EntityNotFoundException::new);
        product.update(data);
        return new DetailedProductDTO(product);
    }

    public DetailedProductDTO update(UUID id, ProductStatusDTO data) {
        Product product = productRepository.findById(id.toString()).orElseThrow(EntityNotFoundException::new);
        product.update(data);
        return new DetailedProductDTO(product);
    }

    public DetailedProductDTO update(UUID id, List<CategoryDTO> data) {
        Product product = productRepository.findById(id.toString()).orElseThrow(EntityNotFoundException::new);
        product.update(data);
        return new DetailedProductDTO(product);
    }

    public void delete(UUID id) {
        productRepository.findById(id.toString()).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteById(id.toString());
    }
}
