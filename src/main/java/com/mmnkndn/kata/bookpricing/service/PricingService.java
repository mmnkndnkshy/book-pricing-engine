package com.mmnkndn.kata.bookpricing.service;

import com.mmnkndn.kata.bookpricing.domain.Basket;
import com.mmnkndn.kata.bookpricing.pricing.*;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private final PricingStrategy pricingStrategy;

    public PricingService() {
        this.pricingStrategy = new GreedyPricingStrategy(); // default
    }

    public PricingService(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public double calculatePrice(Basket basket) {
        return pricingStrategy.calculatePrice(basket);
    }
}