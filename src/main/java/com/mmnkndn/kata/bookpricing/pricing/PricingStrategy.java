package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;

public interface PricingStrategy {
    double calculateBookPrice(BookPricingRequest request);
}