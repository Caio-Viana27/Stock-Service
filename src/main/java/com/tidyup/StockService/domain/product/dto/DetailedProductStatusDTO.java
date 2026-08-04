package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.ProductStatus;
import com.tidyup.StockService.domain.product.entity.StatusValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DetailedProductStatusDTO(
        @NotNull
        Long id,

        @NotBlank
        @Size(max = 50)
        StatusValue status
) {
    public DetailedProductStatusDTO(ProductStatus productStatus) {
        this(productStatus.getId(), productStatus.getStatus());
    }
}

