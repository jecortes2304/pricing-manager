package com.cortestudios.pricingmanager.pricing.infrastructure.util.mapperimpl;

import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.infrastructure.adapter.persistence.entity.PriceEntity;
import com.cortestudios.pricingmanager.shared.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PriceAndPriceEntityMapper implements Mapper<Price, PriceEntity> {
    @Override
    public PriceEntity map(Price input) {
        return PriceEntity.builder()
                .brandId(input.getBrandId())
                .startDate(input.getStartDate())
                .endDate(input.getEndDate())
                .priceList(input.getPriceList())
                .productId(input.getProductId())
                .priority(input.getPriority())
                .price(input.getPrice())
                .currency(input.getCurrency())
                .build();
    }

    @Override
    public Price reverseMap(PriceEntity output) {
        return Price.PriceBuilder.aPrice().
                withBrandId(output.getBrandId())
                .withStartDate(output.getStartDate())
                .withEndDate(output.getEndDate())
                .withPriceList(output.getPriceList())
                .withProductId(output.getProductId())
                .withPriority(output.getPriority())
                .withPrice(output.getPrice())
                .withCurrency(output.getCurrency())
                .build();
    }
}
