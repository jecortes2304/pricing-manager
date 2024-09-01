package com.cortestudios.pricingmanager.pricing.application.usecase;

import com.cortestudios.pricingmanager.pricing.application.port.input.GetPriceUseCasePort;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.application.port.output.PriceRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetPriceUseCase implements GetPriceUseCasePort {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return priceRepositoryPort.findPriceByProductIdAndBrandIdAndDate(
                productId, brandId, applicationDate
        );
    }
}