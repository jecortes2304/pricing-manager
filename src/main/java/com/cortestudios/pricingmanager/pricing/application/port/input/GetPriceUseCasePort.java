package com.cortestudios.pricingmanager.pricing.application.port.input;

import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import java.time.LocalDateTime;

public interface GetPriceUseCasePort {
    Price getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate);
}
