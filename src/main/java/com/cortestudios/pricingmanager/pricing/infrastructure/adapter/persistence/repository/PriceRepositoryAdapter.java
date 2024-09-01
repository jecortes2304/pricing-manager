package com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.repository;

import com.cortestudios.pricingmanager.pricing.domain.exception.PriceNotFoundException;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.application.port.output.PriceRepositoryPort;
import com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.entity.PriceEntity;
import com.cortestudios.pricingmanager.pricing.infrastructure.util.mapperimpl.PriceAndPriceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final H2JpaPriceRepository h2JpaPriceRepository;
    private final PriceAndPriceEntityMapper priceAndPriceEntityMapper;


    @Override
    public Price findPriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime applicationDate) {
        Optional<PriceEntity> priceEntity = h2JpaPriceRepository.findPriceByProductIdAndBrandIdAndDate
                (productId, brandId, applicationDate);

        return priceAndPriceEntityMapper.reverseMap(priceEntity.orElseThrow(
                () -> new PriceNotFoundException("Price not found")
        ));
    }
}
