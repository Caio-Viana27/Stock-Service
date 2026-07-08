package com.tidyup.StockService.domain.product.entity;

import com.tidyup.StockService.domain.product.dto.CategoryDTO;
import com.tidyup.StockService.domain.product.dto.CreateProductDTO;
import com.tidyup.StockService.domain.product.dto.ProductStatusDTO;
import com.tidyup.StockService.domain.product.dto.UpdateProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "ID")
    private final UUID id = UUID.randomUUID();

    @Column(name = "RETAILER_ID")
    private UUID retailerId;

    @Column(name = "SKU")
    private String SKU;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "INVENTORY")
    private Integer inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID")
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @ManyToOne
    @JoinTable(name = "PRODUCT_CATEGORY",
               joinColumns = {@JoinColumn(name = "PRODUCT_ID")},
               inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID")})
    private List<Category> categoryList;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public Product(CreateProductDTO dto) {
        this.retailerId = dto.retailerId();
        this.SKU = dto.SKU();
        this.name = dto.name();
        this.description = dto.description();
        this.price = dto.price();
        this.inventory = dto.inventory();
        this.status = new ProductStatus(dto.status());
        this.brand = new Brand(dto.brand());
        this.categoryList = dto.categoryList().stream().map(Category::new).toList();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public void update(UpdateProductDTO data) {
        this.SKU = data.SKU();
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.inventory = data.inventory();
        this.status = new ProductStatus(data.status());
        this.categoryList = data.categoryList().stream().map(Category::new).toList();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(ProductStatusDTO data) {
        this.status = new ProductStatus(data);
        this.updatedAt = LocalDateTime.now();
    }

    public void update(List<CategoryDTO> data) {
        this.categoryList = data.stream().map(Category::new).toList();
        this.updatedAt = LocalDateTime.now();
    }
}
