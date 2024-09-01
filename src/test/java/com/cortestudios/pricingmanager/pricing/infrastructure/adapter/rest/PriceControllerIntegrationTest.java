package com.cortestudios.pricingmanager.pricing.infrastructure.adapter.rest;

import com.cortestudios.pricingmanager.pricing.infrastructure.dto.request.GetApplicablePriceFilterRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

    private final String URL = "/api/v1/prices/price";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetPriceAt10AMOn14thJune() throws Exception {
        GetApplicablePriceFilterRequestDTO requestDTO = GetApplicablePriceFilterRequestDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .applicationDate(LocalDate.of(2020, 6, 14))
                .applicationTime(LocalTime.of(10, 0, 0))
                .build();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.price").value(35.50));
    }

    @Test
    void testGetPriceAt4PMOn14thJune() throws Exception {
        GetApplicablePriceFilterRequestDTO requestDTO = GetApplicablePriceFilterRequestDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .applicationDate(LocalDate.of(2020, 6, 14))
                .applicationTime(LocalTime.of(16, 0, 0))
                .build();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.price").value(25.45));
    }

    @Test
    void testGetPriceAt9PMOn14thJune() throws Exception {
        GetApplicablePriceFilterRequestDTO requestDTO = GetApplicablePriceFilterRequestDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .applicationDate(LocalDate.of(2020, 6, 14))
                .applicationTime(LocalTime.of(21, 0, 0))
                .build();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.price").value(35.50));
    }

    @Test
    void testGetPriceAt10AMOn15thJune() throws Exception {
        GetApplicablePriceFilterRequestDTO requestDTO = GetApplicablePriceFilterRequestDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .applicationDate(LocalDate.of(2020, 6, 15))
                .applicationTime(LocalTime.of(10, 0, 0))
                .build();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.price").value(30.50));
    }

    @Test
    void testGetPriceAt9PMOn16thJune() throws Exception {
        GetApplicablePriceFilterRequestDTO requestDTO = GetApplicablePriceFilterRequestDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .applicationDate(LocalDate.of(2020, 6, 16))
                .applicationTime(LocalTime.of(21, 0, 0))
                .build();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.price").value(38.95));
    }

    @Test
    void testGetPriceAt10AMOn10thJanuary() throws Exception {
        GetApplicablePriceFilterRequestDTO requestDTO = GetApplicablePriceFilterRequestDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .applicationDate(LocalDate.of(2021, 6, 17))
                .applicationTime(LocalTime.of(10, 0, 0))
                .build();

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]").value("Price not found"));
    }
}