package com.mmnkndn.kata.bookpricing.service;

import java.util.HashSet;
import java.util.Set;

public class PricingService {
    public double calculatePrice(int[] basket) {
        if (basket.length == 0) {
            return 0.0;
        }

        Set<Integer> uniqueBooks = new HashSet<>();
        for (int book : basket) {
            uniqueBooks.add(book);
        }

        int distinctCount = uniqueBooks.size();

        if (distinctCount == 1) {
            return basket.length * 50.0;
        }

        if (distinctCount == 2 && basket.length == 2) {
            return 95.0;
        }

        if (distinctCount == 3 && basket.length == 3) {
            return 135.0;
        }

        return 0.0; // temporary fallback
    }
}
