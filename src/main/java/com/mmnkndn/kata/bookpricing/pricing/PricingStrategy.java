package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.domain.Basket;

public interface PricingStrategy {

    double calculatePrice(Basket basket);
}
