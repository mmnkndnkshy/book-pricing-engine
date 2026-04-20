package com.mmnkndn.kata.bookpricing.service;

import com.mmnkndn.kata.bookpricing.domain.Basket;
import com.mmnkndn.kata.bookpricing.domain.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTest {

    @Test
    void shouldReturnZeorWhenBasketIsEmpty() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of()
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(0.0, price);
    }

    @Test
    void shouldReturnFiftyForSingeBook() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE)
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(50.0, price);
    }

    @Test
    void shouldReturnHundredForTwoSameBooks() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE,
                        Book.CLEAN_CODE
                ));
        double price = pricingService.calculatePrice(basket);
        assertEquals(100.0, price);
    }

    @Test
    void shouldApplyFivePercentDiscountForTwoDifferentBooks() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE,
                        Book.CLEAN_CODER)
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(95.0, price);
    }

    @Test
    void shouldApplyTenPercentDiscountForThreeDifferentBooks() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE,
                        Book.CLEAN_CODER,
                        Book.CLEAN_ARCHITECTURE)
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(135.0, price);
    }

    @Test
    void shouldApplyTwentyPercentDiscountForFourDifferentBooks() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE,
                        Book.CLEAN_CODER,
                        Book.CLEAN_ARCHITECTURE,
                        Book.TDD_BY_EXAMPLE)
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(160.0, price);
    }

    @Test
    void shouldApplyTwentyFivePercentDiscountForFiveDifferentBooks() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE,
                        Book.CLEAN_CODER,
                        Book.CLEAN_ARCHITECTURE,
                        Book.TDD_BY_EXAMPLE,
                        Book.LEGACY_CODE)
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(187.5, price);
    }

    @Test
    void shouldCalculateOptimalPriceForComplexBasket() {
        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE,
                        Book.CLEAN_CODE,
                        Book.CLEAN_CODER,
                        Book.CLEAN_CODER,
                        Book.CLEAN_ARCHITECTURE,
                        Book.CLEAN_ARCHITECTURE,
                        Book.TDD_BY_EXAMPLE,
                        Book.LEGACY_CODE)
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(320.0, price);
    }

    @Test
    void shouldReturnHundredForTwoSameBooks_usingBasket() {

        PricingService pricingService = new PricingService();
        Basket basket = new Basket(
                List.of(Book.CLEAN_CODE, Book.CLEAN_CODE)
        );
        double price = pricingService.calculatePrice(basket);
        assertEquals(100.0, price);
    }
}
