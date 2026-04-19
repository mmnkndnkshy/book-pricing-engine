package com.mmnkndn.kata.bookpricing.service;

public class PricingService {
    public double calculatePrice(int[] basket) {
        if (basket.length == 0) {
            return 0.0;
        }

        if (basket.length == 1) {
            return 50.0;
        }

        if (basket.length == 2) {
            if (basket[0] != basket[1]) {
                return 95.0; // 5% discount for different books
            }
            return 100.0; // same books
        }

        return 0.0; // temporary fallback
    }
}
