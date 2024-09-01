package com.cortestudios.pricingmanager.pricing.infrastructure.adapter.rest;

import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.*;
import com.cortestudios.pricingmanager.pricing.application.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Price")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Validated
public class PriceController {

    private final PriceService priceService;

    @Operation(summary = "Get list of prices", description = "Get a list of prices by params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prices fetched successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponsePriceListResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    @GetMapping(path = "/v1/prices/prices/{productId}/{brandId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPrices(
            @PathVariable Long productId,
            @PathVariable Long brandId,
            @Min(2) @RequestParam(defaultValue = "2", required = false) Integer pageSize,
            @Min(0) @RequestParam(defaultValue = "0", required = false) Integer page) {

        Pageable paging = PageRequest.of(page, pageSize);
        List<PriceResponseDTO> priceResponseDTOs = priceService.getAllPricesByParams(productId, brandId, paging);

        return ResponseEntity.ok(StandardOkResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .result(priceResponseDTOs)
                .build());
    }


    @Operation(summary = "Get a price", description = "Get a price by params and date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prices fetched successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponsePriceResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    @GetMapping(path = "/v1/prices/price/{productId}/{brandId}/{applicationDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPrice(
            @NotNull(message = "productId is required") @Positive(message = "productId must be positive")
            @Digits(integer = 10, fraction = 0, message = "productId must be a number")
            @PathVariable Long productId,
            @NotNull(message = "brandId is required") @Positive(message = "brandId must be positive")
            @Digits(integer = 10, fraction = 0, message = "brandId must be a number")
            @PathVariable Long brandId,
            @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
            @Schema(type = "string", example = "2020-06-14-10:00:00")
            @NotNull(message = "applicationDate is required")
            @PathVariable LocalDateTime applicationDate
    ) {
        PriceResponseDTO priceResponseDTO = priceService.getApplicablePrice(productId, brandId, applicationDate);
        return ResponseEntity.ok(StandardOkResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .result(priceResponseDTO)
                .build());
    }
}
