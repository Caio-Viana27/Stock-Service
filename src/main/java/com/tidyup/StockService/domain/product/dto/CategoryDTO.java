package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryDTO(
        @NotBlank
        @Size(max = 50)
        String category
) {
    public CategoryDTO(Category category) {
        this(category.getCategory());
    }
}
