package com.cortestudios.pricingmanager.pricing.application.service;

import com.cortestudios.pricingmanager.pricing.application.usecase.GetPriceUseCase;
import com.cortestudios.pricingmanager.pricing.application.usecase.GetManyPricesUseCase;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.PriceResponseDTO;
import com.cortestudios.pricingmanager.pricing.infrastructure.util.mapperimpl.PriceAndPriceResponseDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final GetPriceUseCase getPriceUseCase;
    private final GetManyPricesUseCase getManyPricesUseCase;
    private final PriceAndPriceResponseDTOMapper priceAndPriceResponseDTOMapper;

    public List<PriceResponseDTO> getAllPricesByParams(final Long productId, final Long brandId, final Pageable pageable) {
        final List<Price> prices = getManyPricesUseCase.getAllPricesByParams(productId, brandId, pageable).toList();
        return prices.stream()
                .map(priceAndPriceResponseDTOMapper::map)
                .toList();
    }

    public PriceResponseDTO getApplicablePrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        final Price price = getPriceUseCase.getApplicablePrice(productId, brandId, applicationDate);
        return priceAndPriceResponseDTOMapper.map(price);
    }
}
