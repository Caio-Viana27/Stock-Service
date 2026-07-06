package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.ProductStatus;
import com.tidyup.StockService.domain.product.entity.StatusValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductStatusDTO(
        @NotBlank
        @Size(max = 50)
        StatusValue status
) {
    public ProductStatusDTO(ProductStatus productStatus) {
        this(productStatus.getStatus());
    }
}
