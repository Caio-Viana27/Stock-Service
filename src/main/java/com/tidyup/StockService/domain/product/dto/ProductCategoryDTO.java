package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.Category;
import com.tidyup.StockService.domain.product.entity.ProductCategory;
import jakarta.validation.constraints.NotNull;

public record ProductCategoryDTO(
        @NotNull
        Long id,

        @NotNull
        Category category
) {
    public ProductCategoryDTO(ProductCategory productCategory) {
        this(productCategory.getId(), productCategory.getCategory());
    }
}
