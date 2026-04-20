package com.mmnkndn.kata.bookpricing.service;

import com.mmnkndn.kata.bookpricing.domain.Basket;
import com.mmnkndn.kata.bookpricing.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PricingServiceTest {

    @Autowired
    private PricingService pricingService;

    @Test
    void shouldReturnZeroWhenBasketIsEmpty() {
        Basket basket = new Basket(List.of());

        double price = pricingService.calculatePrice(basket);

        assertEquals(0.0, price);
    }

    @Test
    void shouldReturnFiftyForSingleBook() {
        Basket basket = new Basket(List.of(Book.CLEAN_CODE));

        double price = pricingService.calculatePrice(basket);

        assertEquals(50.0, price);
    }

    @Test
    void shouldReturnHundredForTwoSameBooks() {
        Basket basket = new Basket(List.of(
                Book.CLEAN_CODE,
                Book.CLEAN_CODE
        ));

        double price = pricingService.calculatePrice(basket);

        assertEquals(100.0, price);
    }

    @Test
    void shouldApplyFivePercentDiscountForTwoDifferentBooks() {
        Basket basket = new Basket(List.of(
                Book.CLEAN_CODE,
                Book.CLEAN_CODER
        ));

        double price = pricingService.calculatePrice(basket);

        assertEquals(95.0, price);
    }

    @Test
    void shouldApplyTenPercentDiscountForThreeDifferentBooks() {
        Basket basket = new Basket(List.of(
                Book.CLEAN_CODE,
                Book.CLEAN_CODER,
                Book.CLEAN_ARCHITECTURE
        ));

        double price = pricingService.calculatePrice(basket);

        assertEquals(135.0, price);
    }

    @Test
    void shouldApplyTwentyPercentDiscountForFourDifferentBooks() {
        Basket basket = new Basket(List.of(
                Book.CLEAN_CODE,
                Book.CLEAN_CODER,
                Book.CLEAN_ARCHITECTURE,
                Book.TDD_BY_EXAMPLE
        ));

        double price = pricingService.calculatePrice(basket);

        assertEquals(160.0, price);
    }

    @Test
    void shouldApplyTwentyFivePercentDiscountForFiveDifferentBooks() {
        Basket basket = new Basket(List.of(
                Book.CLEAN_CODE,
                Book.CLEAN_CODER,
                Book.CLEAN_ARCHITECTURE,
                Book.TDD_BY_EXAMPLE,
                Book.LEGACY_CODE
        ));

        double price = pricingService.calculatePrice(basket);

        assertEquals(187.5, price);
    }

    @Test
    void shouldCalculateOptimalPriceForComplexBasket() {
        Basket basket = new Basket(List.of(
                Book.CLEAN_CODE,
                Book.CLEAN_CODE,
                Book.CLEAN_CODER,
                Book.CLEAN_CODER,
                Book.CLEAN_ARCHITECTURE,
                Book.CLEAN_ARCHITECTURE,
                Book.TDD_BY_EXAMPLE,
                Book.LEGACY_CODE
        ));

        double price = pricingService.calculatePrice(basket);

        assertEquals(320.0, price);
    }
}