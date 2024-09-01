package com.cortestudios.pricingmanager.pricing.infrastructure.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StandardOkResponse<T> extends StandardResponse {
    private T result;
}
