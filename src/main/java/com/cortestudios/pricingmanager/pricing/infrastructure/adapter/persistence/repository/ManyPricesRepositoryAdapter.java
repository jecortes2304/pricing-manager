package com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.repository;

import com.cortestudios.pricingmanager.pricing.application.port.output.ManyPricesRepositoryPort;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.entity.PriceEntity;
import com.cortestudios.pricingmanager.pricing.infrastructure.util.mapperimpl.PriceAndPriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ManyPricesRepositoryAdapter implements ManyPricesRepositoryPort {

    private final H2JpaPriceRepository h2JpaPriceRepository;
    private final PriceAndPriceEntityMapper priceAndPriceEntityMapper;


    @Override
    public Page<Price> findAllPricesByProductAndBrandId(Long productId, Long brandId, Pageable pageable) {
        Page<PriceEntity> entityPricesPage = h2JpaPriceRepository
                .findPricesByProductIdAndBrandId(productId, brandId, pageable);
        return entityPricesPage.map(priceAndPriceEntityMapper::reverseMap);
    }
}
