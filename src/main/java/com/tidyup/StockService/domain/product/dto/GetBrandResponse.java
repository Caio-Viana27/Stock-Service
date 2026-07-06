package com.tidyup.StockService.domain.product.dto;

import com.tidyup.StockService.domain.product.entity.Brand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GetBrandResponse(
        @NotBlank
        @Size(max = 50)
        String brand
) {
    public GetBrandResponse(Brand brand) {
        this(brand.getBrand());
    }
}