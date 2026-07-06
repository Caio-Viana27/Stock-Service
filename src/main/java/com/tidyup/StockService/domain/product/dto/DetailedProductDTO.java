package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DetailedProductDTO(
        @NotBlank @Size(max = 32, min = 32)
        UUID Id,

        @NotBlank @Size(max = 32, min = 32)
        UUID retailerId,

        @NotBlank @Size(max = 32, min = 32)
        String SKU,

        @NotBlank @Size(max = 100)
        String name,

        @NotBlank @Size(max = 250)
        String description,

        @NotNull @PositiveOrZero
        BigDecimal price,

        @NotNull @PositiveOrZero
        Integer inventory,

        @NotNull
        ProductStatusDTO status,

        @NotNull
        GetBrandResponse brand,

        @NotNull
        List<CategoryDTO> categoryList,

        @NotNull
        LocalDateTime createdAt
) {
    public DetailedProductDTO(Product product) {
        this(product.getId(),
             product.getRetailerId(),
             product.getSKU(),
             product.getName(),
             product.getDescription(),
             product.getPrice(),
             product.getInventory(),
             new ProductStatusDTO(product.getStatus()),
             new GetBrandResponse(product.getBrand()),
             product.getCategoryList().stream().map(CategoryDTO::new).toList(),
             product.getCreatedAt()
        );
    }
}
