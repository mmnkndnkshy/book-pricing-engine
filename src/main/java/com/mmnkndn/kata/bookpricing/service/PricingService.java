package com.mmnkndn.kata.bookpricing.service;

import com.mmnkndn.kata.bookpricing.domain.Basket;
import com.mmnkndn.kata.bookpricing.pricing.PricingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private final PricingStrategy pricingStrategy;

    public PricingService(
            @Qualifier("greedyPricingStrategy") PricingStrategy pricingStrategy
    ) {
        this.pricingStrategy = pricingStrategy;
    }

    public double calculatePrice(Basket basket) {
        return pricingStrategy.calculatePrice(basket);
    }
}