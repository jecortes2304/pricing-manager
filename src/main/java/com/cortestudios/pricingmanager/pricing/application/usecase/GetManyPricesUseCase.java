package com.cortestudios.pricingmanager.pricing.application.usecase;

import com.cortestudios.pricingmanager.pricing.application.port.input.GetManyPricesUseCasePort;
import com.cortestudios.pricingmanager.pricing.application.port.output.ManyPricesRepositoryPort;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetManyPricesUseCase implements GetManyPricesUseCasePort {

    private final ManyPricesRepositoryPort manyPricesRepositoryPort;

    public GetManyPricesUseCase(ManyPricesRepositoryPort manyPricesRepositoryPort) {
        this.manyPricesRepositoryPort = manyPricesRepositoryPort;
    }

    @Override
    public Page<Price> getAllPricesByParams(Long productId, Long brandId, Pageable pageable) {
        return manyPricesRepositoryPort.findAllPricesByProductAndBrandId(productId, brandId, pageable);
    }
}
