package com.tidyup.StockService.Service;

import com.tidyup.StockService.Repository.BrandRepository;
import com.tidyup.StockService.domain.product.dto.BrandDTO;
import com.tidyup.StockService.domain.product.entity.Brand;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public BrandDTO create(@Valid BrandDTO brandDTO) {
        var brand = new Brand(brandDTO);
        brandRepository.save(brand);
        return new BrandDTO(brand);
    }

    public Page<BrandDTO> getAll(Pageable pageable) {
        return brandRepository.findAll(pageable).map(BrandDTO::new);
    }

    public BrandDTO getById(Long id) {
        return brandRepository.findById(id).map(BrandDTO::new).orElseThrow(EntityNotFoundException::new);
    }

    public BrandDTO update(Long id, @Valid BrandDTO brandDTO) {
        Brand brand = brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        brand.update(brandDTO);
        return new BrandDTO(brand);
    }

    public void delete(Long id) {
        brandRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        brandRepository.deleteById(id);
    }
}
