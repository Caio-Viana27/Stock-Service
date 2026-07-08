package com.tidyup.StockService.Service;

import com.tidyup.StockService.Repository.BrandRepository;
import com.tidyup.StockService.Repository.ProductCategoryRepository;
import com.tidyup.StockService.Repository.ProductRepository;
import com.tidyup.StockService.domain.product.dto.*;
import com.tidyup.StockService.domain.product.entity.Product;
import com.tidyup.StockService.domain.product.entity.ProductCategory;
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

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public DetailedProductDTO createProduct(CreateProductDTO dto) {
        var product = new Product(dto);
        var brand = product.getBrand();

        product.getProductCategoryList().forEach(category -> productCategoryRepository.save(category));
        brandRepository.saveAndFlush(brand);
        productRepository.save(product);

        return new DetailedProductDTO(product);
    }

    public Page<SimpleProductDTO> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(SimpleProductDTO::new);
    }

    public SimpleProductDTO getProduct(UUID id) {
        return productRepository.findById(id).map(SimpleProductDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public DetailedProductDTO update(UUID id, UpdateProductDTO data) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.update(data);
        return new DetailedProductDTO(product);
    }

    public DetailedProductDTO update(UUID id, ProductStatusDTO data) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.update(data);
        return new DetailedProductDTO(product);
    }

    public DetailedProductDTO update(UUID id, List<ProductCategoryDTO> data) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.update(data);
        return new DetailedProductDTO(product);
    }

    public void delete(UUID id) {
        productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteById(id);
    }
}
