package com.tidyup.StockService.domain.product.entity;

import com.tidyup.StockService.domain.product.dto.BrandDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BRANDS")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RETAILER_ID")
    private UUID retailerId;

    @Column(name = "BRAND")
    private String brand;

    public Brand(BrandDTO dto) {
        this.retailerId = dto.retailerId();
        this.brand = dto.brand();
    }

    public void update(@Valid BrandDTO brandDTO) {
        this.retailerId = brandDTO.retailerId();
        this.brand = brandDTO.brand();
    }

    public boolean equals(BrandDTO dto) {
        return this.id.compareTo(dto.id()) == 0 && this.retailerId.compareTo(dto.retailerId()) == 0 && this.brand.equals(dto.brand());
    }
}
