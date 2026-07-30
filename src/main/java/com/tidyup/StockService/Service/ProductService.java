package com.tidyup.StockService.Service;

import com.tidyup.StockService.Repository.BrandRepository;
import com.tidyup.StockService.Repository.ProductCategoryRepository;
import com.tidyup.StockService.Repository.ProductRepository;
import com.tidyup.StockService.domain.product.dto.*;
import com.tidyup.StockService.domain.product.entity.Brand;
import com.tidyup.StockService.domain.product.entity.Product;
import com.tidyup.StockService.domain.product.entity.ProductCategory;
import com.tidyup.StockService.infrastruture.exception.EntityDoesNotExistException;
import com.tidyup.StockService.infrastruture.exception.EntityDoesNotMatchException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    public DetailedProductDTO create(CreateProductDTO dto) {
        var product = new Product(dto);
        product.setBrand(validateBrand(dto.brand()));
        product.setProductCategoryList(validateProductCategoryList(dto.categoriesList()));
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

    private Brand validateBrand(BrandDTO dto) {
        Optional<Brand> brandOptional = brandRepository.findById(dto.id());

        if (brandOptional.isEmpty())
            throw new EntityDoesNotExistException("Brand entity with id: " + dto.id() + " doesn't exists!");
        Brand brandEntity = brandOptional.get();

        if (!brandEntity.equals(dto))
            throw new EntityDoesNotMatchException("Brand attributes don't match the fields of the Brand entity with the Id: " + brandEntity.getId());
        return brandEntity;
    }

    private List<ProductCategory> validateProductCategoryList(List<ProductCategoryDTO> list) {
        List<ProductCategory> validProductCategoryEntities = new ArrayList<>();

        list.forEach(productCategoryDTO -> {
            Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(productCategoryDTO.id());
            if (productCategoryOptional.isEmpty())
                throw new EntityDoesNotExistException("Brand entity with id: " + productCategoryDTO.id() + " doesn't exists!");
            ProductCategory productCategoryEntity = productCategoryOptional.get();

            if (!productCategoryEntity.equals(productCategoryDTO))
                throw new EntityDoesNotMatchException("Brand attributes don't match the fields of the Brand entity with the Id: " + productCategoryEntity.getId());
            validProductCategoryEntities.add(productCategoryEntity);
        });

        return validProductCategoryEntities;
    }
}
