package com.cortestudios.pricingmanager.pricing.infrastructure.util.mapperimpl;

import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.entity.PriceEntity;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.PriceResponseDTO;
import com.cortestudios.pricingmanager.shared.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PriceAndPriceResponseDTOMapper implements Mapper<Price, PriceResponseDTO> {
    @Override
    public PriceResponseDTO map(Price input) {
        return PriceResponseDTO.builder()
                .brandId(input.getBrandId())
                .productId(input.getProductId())
                .price(input.getPrice())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .currency(input.getCurrency())
                .build();
    }

    @Override
    public Price reverseMap(PriceResponseDTO output) {
        return Price.PriceBuilder.aPrice().
                withBrandId(output.getBrandId())
                .withStartDate(output.getStartDate())
                .withEndDate(output.getEndDate())
                .withProductId(output.getProductId())
                .withPrice(output.getPrice())
                .withPriority(1)
                .withPriceList(1L)
                .withCurrency(output.getCurrency())
                .build();
    }
}
