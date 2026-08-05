package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.ProductStatus;
import com.tidyup.StockService.domain.product.entity.StatusValue;
import jakarta.validation.constraints.NotNull;

public record DetailedProductStatusDTO(
        @NotNull
        Long id,

        @NotNull
        StatusValue status
) {
    public DetailedProductStatusDTO(ProductStatus productStatus) {
        this(productStatus.getId(), productStatus.getStatus());
    }
}

