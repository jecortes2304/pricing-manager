package com.cortestudios.pricingmanager.pricing.application.service;

import com.cortestudios.pricingmanager.pricing.application.usecase.GetPriceUseCase;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import com.cortestudios.pricingmanager.pricing.infrastructure.dto.response.PriceResponseDTO;
import com.cortestudios.pricingmanager.pricing.infrastructure.util.mapperimpl.PriceAndPriceResponseDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    @Mock
    private GetPriceUseCase getPriceUseCase;

    @Mock
    private PriceAndPriceResponseDTOMapper priceAndPriceResponseDTOMapper;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApplicablePrice() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDateTime = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        Price price = new Price(1L, applicationDateTime, applicationDateTime.plusHours(2), 1L, 35455L, 0, 35.50, "EUR");
        PriceResponseDTO responseDTO = new PriceResponseDTO();

        when(getPriceUseCase.getApplicablePrice(35455L, 1L, applicationDateTime)).thenReturn(price);
        when(priceAndPriceResponseDTOMapper.map(price)).thenReturn(responseDTO);

        PriceResponseDTO result = priceService.getApplicablePrice(productId, brandId, applicationDateTime);

        assertNotNull(result);
        verify(getPriceUseCase, times(1)).getApplicablePrice(35455L, 1L, applicationDateTime);
        verify(priceAndPriceResponseDTOMapper, times(1)).map(price);
    }
}
