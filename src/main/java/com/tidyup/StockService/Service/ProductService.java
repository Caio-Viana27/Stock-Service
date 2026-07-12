package com.tidyup.StockService.Service;

import com.tidyup.StockService.Repository.BrandRepository;
import com.tidyup.StockService.Repository.ProductCategoryRepository;
import com.tidyup.StockService.Repository.ProductRepository;
import com.tidyup.StockService.Repository.ProductStatusRepository;
import com.tidyup.StockService.domain.product.dto.*;
import com.tidyup.StockService.domain.product.entity.Brand;
import com.tidyup.StockService.domain.product.entity.Product;
import com.tidyup.StockService.infrastruture.exception.EntityDoesNotExistException;
import com.tidyup.StockService.infrastruture.exception.EntityDoesNotMatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductStatusRepository productStatusRepository;


    public DetailedProductDTO create(CreateProductDTO dto) {
        var product = new Product(dto);

        Optional<Brand> optional = brandRepository.findById(dto.brand().id());
        if (optional.isEmpty()) throw new EntityDoesNotExistException("Brand entity with id: " + dto.brand().id() + " doesn't exists!");
        Brand brand = optional.get();
        if (!brand.equals(dto.brand())) throw new EntityDoesNotMatchException("Brand attributes Don't match the fields of the Brand entity with the Id: " + brand.getId());
        product.setBrand(brand);



        productRepository.save(product);
        return new DetailedProductDTO(product);
    }

    public Page<SimpleProductDTO> getAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(SimpleProductDTO::new);
    }

    public SimpleProductDTO getById(UUID id) {
        return productRepository.findById(id).map(SimpleProductDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public DetailedProductDTO update(UUID id, UpdateProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.update(dto);
        return new DetailedProductDTO(product);
    }

    public DetailedProductDTO update(UUID id, ProductStatusDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.update(dto);
        return new DetailedProductDTO(product);
    }

    public DetailedProductDTO update(UUID id, List<ProductCategoryDTO> dtoList) {
        Product product = productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        product.update(dtoList);
        return new DetailedProductDTO(product);
    }

    public void delete(UUID id) {
        productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        productRepository.deleteById(id);
    }
}
