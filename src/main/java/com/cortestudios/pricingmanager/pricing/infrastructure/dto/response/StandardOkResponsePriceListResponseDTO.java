package com.cortestudios.pricingmanager.pricing.infrastructure.dto.response;

import java.util.List;

public class StandardOkResponsePriceListResponseDTO extends StandardOkResponse<List<PriceResponseDTO>> {
    protected StandardOkResponsePriceListResponseDTO(StandardOkResponseBuilder<List<PriceResponseDTO>, ?, ?> b) {
        super(b);
    }
}