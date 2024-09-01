package com.cortestudios.pricingmanager.pricing.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponseDTO {

    private Long productId;
    private Long brandId;
    private Double price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
