package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.Category;
import com.tidyup.StockService.domain.product.entity.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductCategoryDTO(
        @NotNull
        Long id,

        @NotBlank
        @Size(max = 50)
        Category category
) {
    public ProductCategoryDTO(ProductCategory productCategory) {
        this(productCategory.getCategory());
    }
}
