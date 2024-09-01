package com.cortestudios.pricingmanager.pricing.infrastructure.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetApplicablePriceFilterRequestDTO {

    @NotNull(message = "productId is required")
    @Positive(message = "productId must be positive")
    @Digits(integer = 10, fraction = 0, message = "productId must be a number")
    private Long productId;
    @Digits(integer = 10, fraction = 0, message = "brandId must be a number")
    @NotNull(message = "brandId is required")
    @Positive(message = "brandId must be positive")
    private Long brandId;
    @NotNull(message = "applicationDate is required")
    @Schema(type = "string", pattern = "yyyy-MM-dd", example = "2020-10-14")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;
    @NotNull(message = "applicationTime is required")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", pattern = "HH:mm:ss", example = "10:00:00")
    private LocalTime applicationTime;
}
