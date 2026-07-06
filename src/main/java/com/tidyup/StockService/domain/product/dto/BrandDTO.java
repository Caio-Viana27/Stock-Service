package com.tidyup.StockService.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record BrandDTO(
        @NotNull
        UUID retailerId,

        @NotBlank
        @Size(max = 50)
        String brand
) {
}
