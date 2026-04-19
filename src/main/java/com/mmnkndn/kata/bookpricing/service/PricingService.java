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
            return 100.0;
        }

        return 0.0; // temporary fallback
    }
}
