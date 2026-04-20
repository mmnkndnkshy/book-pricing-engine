package com.mmnkndn.kata.bookpricing.service;


import com.mmnkndn.kata.bookpricing.api.model.Book;
import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;

import com.mmnkndn.kata.bookpricing.api.model.BookPricingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PricingServiceTest {

    @Autowired
    private BookPricingService pricingService;

    private BookPricingRequest buildRequest(Map<String, Integer> items) {

        BookPricingRequest request = new BookPricingRequest();
        request.setItems(items);

        return request;
    }


    @Test
    void shouldReturnZeroWhenBasketIsEmpty() {
        BookPricingRequest request = buildRequest(Map.of());

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(0.0, response.getPricing().getFinalPrice());
    }

    @Test
    void shouldReturnFiftyForSingleBook() {
        BookPricingRequest request = buildRequest(Map.of("CLEAN_CODE", 1));

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(50.0, response.getPricing().getFinalPrice());
    }

    @Test
    void shouldReturnHundredForTwoSameBooks() {
        BookPricingRequest request = buildRequest(Map.of("CLEAN_CODE", 2));

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(100.0, response.getPricing().getFinalPrice());
    }

    @Test
    void shouldApplyFivePercentDiscountForTwoDifferentBooks() {
        BookPricingRequest request = buildRequest(Map.of(
                "CLEAN_CODE", 1,
                "CLEAN_CODER", 1
        ));

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(95.0, response.getPricing().getFinalPrice());
    }

    @Test
    void shouldApplyTenPercentDiscountForThreeDifferentBooks() {
        BookPricingRequest request = buildRequest(Map.of(
                "CLEAN_CODE", 1,
                "CLEAN_CODER", 1,
                "CLEAN_ARCHITECTURE", 1
        ));

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(135.0, response.getPricing().getFinalPrice());
    }

    @Test
    void shouldApplyTwentyPercentDiscountForFourDifferentBooks() {
        BookPricingRequest request = buildRequest(Map.of(
                "CLEAN_CODE", 1,
                "CLEAN_CODER", 1,
                "CLEAN_ARCHITECTURE", 1,
                "TDD_BY_EXAMPLE",1
        ));

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(160.0, response.getPricing().getFinalPrice());
    }

    @Test
    void shouldApplyTwentyFivePercentDiscountForFiveDifferentBooks() {
        BookPricingRequest request = buildRequest(Map.of(
                "CLEAN_CODE", 1,
                "CLEAN_CODER", 1,
                "CLEAN_ARCHITECTURE", 1,
                "TDD_BY_EXAMPLE",1,
                "LEGACY_CODE",1
        ));

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(187.5, response.getPricing().getFinalPrice());
    }

    @Test
    void shouldCalculateOptimalPriceForComplexBasket() {
        BookPricingRequest request = buildRequest(Map.of(
                "CLEAN_CODE", 2,
                "CLEAN_CODER", 2,
                "CLEAN_ARCHITECTURE", 2,
                "TDD_BY_EXAMPLE",1,
                "LEGACY_CODE",1
        ));

        BookPricingResponse response = pricingService.calculateBookPrice(request);

        assertEquals(320.0, response.getPricing().getFinalPrice());
    }
}