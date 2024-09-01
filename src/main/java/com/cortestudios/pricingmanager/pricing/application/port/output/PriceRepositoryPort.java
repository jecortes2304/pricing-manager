package com.cortestudios.pricingmanager.pricing.application.port.output;

import com.cortestudios.pricingmanager.pricing.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceRepositoryPort {
    Price findPriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime applicationDate);
}
