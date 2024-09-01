package com.cortestudios.pricingmanager.pricing.infrastructure.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StandardResponse {

    private int statusCode;
    private String statusMessage;

}