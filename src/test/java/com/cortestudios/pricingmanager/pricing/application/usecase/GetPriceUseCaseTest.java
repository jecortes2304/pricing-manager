package com.cortestudios.pricingmanager.pricing.application.usecase;


import com.cortestudios.pricingmanager.pricing.application.port.output.PriceRepositoryPort;
import com.cortestudios.pricingmanager.pricing.domain.exception.PriceNotFoundException;
import com.cortestudios.pricingmanager.pricing.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPriceUseCaseTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApplicablePriceSuccess() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        Price expectedPrice = new Price(brandId, applicationDate, applicationDate.plusHours(2), 1L, productId, 0, 35.50, "EUR");

        when(priceRepositoryPort.findPriceByProductIdAndBrandIdAndDate(productId, brandId, applicationDate))
                .thenReturn(expectedPrice);

        Price result = getPriceUseCase.getApplicablePrice(productId, brandId, applicationDate);

        assertNotNull(result);
        assertEquals(expectedPrice, result);

        verify(priceRepositoryPort, times(1)).findPriceByProductIdAndBrandIdAndDate(productId, brandId, applicationDate);
    }

    @Test
    void testGetApplicablePriceNotFound() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        when(priceRepositoryPort.findPriceByProductIdAndBrandIdAndDate(productId, brandId, applicationDate))
                .thenThrow(new PriceNotFoundException("Price not found"));

        assertThrows(PriceNotFoundException.class, () ->
                getPriceUseCase.getApplicablePrice(productId, brandId, applicationDate));

        verify(priceRepositoryPort, times(1)).findPriceByProductIdAndBrandIdAndDate(productId, brandId, applicationDate);
    }
}
