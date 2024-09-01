package com.cortestudios.pricingmanager.pricing.application.port.input;

import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetManyPricesUseCasePort {
    Page<Price> getAllPricesByParams(Long productId, Long brandId, Pageable pageable);
}
