package com.cortestudios.pricingmanager.pricing.application.service;

import com.cortestudios.pricingmanager.pricing.application.usecase.GetPriceUseCase;
import com.cortestudios.pricingmanager.pricing.application.usecase.GetManyPricesUseCase;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.request.GetApplicablePriceFilterRequestDTO;
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

    public List<PriceResponseDTO> getAllPricesByParams(Long productId, Long brandId, Pageable pageable) {
        List<Price> prices = getManyPricesUseCase.getAllPricesByParams(productId, brandId, pageable).toList();

        return prices.stream()
                .map(priceAndPriceResponseDTOMapper::map)
                .toList();
    }

    public PriceResponseDTO getApplicablePrice(GetApplicablePriceFilterRequestDTO getApplicablePriceFilterRequestDTO) {
        Long productId = getApplicablePriceFilterRequestDTO.getProductId();
        Long brandId = getApplicablePriceFilterRequestDTO.getBrandId();
        LocalDateTime applicationDateTime = LocalDateTime.of(
                getApplicablePriceFilterRequestDTO.getApplicationDate(),
                getApplicablePriceFilterRequestDTO.getApplicationTime()
        );

        Price price = getPriceUseCase.getApplicablePrice(productId, brandId, applicationDateTime);

        return priceAndPriceResponseDTOMapper.map(price);
    }
}
