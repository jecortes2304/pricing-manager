package com.cortestudios.pricingmanager.pricing.infrastructure.adapter.rest;

import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.PriceResponseDTO;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.StandardErrorResponse;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.StandardOkResponse;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.request.GetApplicablePriceFilterRequestDTO;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.StandardOkResponsePriceResponseDTOList;
import com.cortestudios.pricingmanager.pricing.application.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Price")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Validated
public class PriceController {

    private final PriceService priceService;

    @GetMapping(path = "/v1/prices/prices/{productId}/{brandId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get list of prices", description = "Get a list of prices by params")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prices fetched successfully", content = @Content(schema = @Schema(implementation = StandardOkResponsePriceResponseDTOList.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<?> getPrices(@PathVariable Long productId, @PathVariable Long brandId, @Min(2) @RequestParam(defaultValue = "2", required = false) Integer pageSize, @Min(0) @RequestParam(defaultValue = "0", required = false) Integer page) {
        Pageable paging = PageRequest.of(page, pageSize);
        List<PriceResponseDTO> priceResponseDTOs = priceService.getAllPricesByParams(productId, brandId, paging);
        return ResponseEntity.ok(StandardOkResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .result(priceResponseDTOs)
                .build());
    }

    @PostMapping(path = "/v1/prices/price", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get a price", description = "Get a price by params and date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prices fetched successfully",  content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<?> getPrice(@Valid @RequestBody GetApplicablePriceFilterRequestDTO getApplicablePriceFilterRequestDTO) {
        PriceResponseDTO priceResponseDTOs = priceService.getApplicablePrice(getApplicablePriceFilterRequestDTO);
        return ResponseEntity.ok(StandardOkResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .result(priceResponseDTOs)
                .build());
    }
}
