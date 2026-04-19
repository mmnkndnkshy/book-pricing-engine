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
}
