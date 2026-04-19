package com.mmnkndn.kata.bookpricing.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {

    @Test
    void shouldReturnZeorWhenBasketIsEmpty(){
        PricingService pricingService = new PricingService();
        double price = pricingService.calculatePrice(new int[]{});
        assertEquals(0.0, price);
    }

    @Test
    void shouldReturnFiftyForSingeBook(){
        PricingService pricingService = new PricingService();
        double price = pricingService.calculatePrice(new int[]{1});
        assertEquals(50.0, price);
    }

    @Test
    void shouldReturnHundredForTwoSameBooks() {
        PricingService pricingService = new PricingService();
        double price = pricingService.calculatePrice(new int[]{1, 1});
        assertEquals(100.0, price);
    }

    @Test
    void shouldApplyFivePercentDiscountForTwoDifferentBooks() {
        PricingService pricingService = new PricingService();
        double price = pricingService.calculatePrice(new int[]{1, 2});
        assertEquals(95.0, price);
    }

    @Test
    void shouldApplyTenPercentDiscountForThreeDifferentBooks() {
        PricingService pricingService = new PricingService();
        double price = pricingService.calculatePrice(new int[]{1, 2, 3});
        assertEquals(135.0, price);
    }

    @Test
    void shouldApplyTwentyPercentDiscountForFourDifferentBooks() {
        PricingService pricingService = new PricingService();
        double price = pricingService.calculatePrice(new int[]{1, 2, 3, 4});
        assertEquals(160.0, price);
    }
}
