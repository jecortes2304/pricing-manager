package com.cortestudios.pricingmanager.pricing.application.port.output;

import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManyPricesRepositoryPort {
    Page<Price> findAllPricesByProductAndBrandId(Long productId, Long brandId, Pageable pageable);
}
