package com.mmnkndn.kata.bookpricing.api.mapper;

import com.mmnkndn.kata.bookpricing.api.model.*;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class BookPricingResponseMapper {

    public BookPricingResponse toResponse(BookPricingRequest request, double totalPrice) {

        Map<String, Integer> items =
                request.getItems() != null ? request.getItems() : Map.of();

        int totalItems = items.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        int uniqueBooks = items.size();

        BasketSummary summary = new BasketSummary();
        summary.setTotalItems(totalItems);
        summary.setUniqueBooks(uniqueBooks);

        Pricing pricing = new Pricing();

        double basePrice = totalItems * 50.0;
        double finalPrice = totalPrice;
        double discount = basePrice - finalPrice;

        pricing.setBasePrice(basePrice);
        pricing.setFinalPrice(finalPrice);
        pricing.setDiscountAmount(discount);

        BookPricingResponse response = new BookPricingResponse();
        response.setBasketSummary(summary);
        response.setPricing(pricing);
        response.setGroupBreakdown(java.util.List.of());

        return response;
    }
}