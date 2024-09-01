package com.cortestudios.pricingmanager.pricing.infrastructure.dto.response;

import java.util.List;

public class StandardOkResponsePriceResponseDTOList extends StandardOkResponse<List<PriceResponseDTO>> {
    protected StandardOkResponsePriceResponseDTOList(StandardOkResponseBuilder<List<PriceResponseDTO>, ?, ?> b) {
        super(b);
    }
}