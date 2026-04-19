package com.mmnkndn.kata.bookpricing.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PricingService {

    private static final double BOOK_PRICE = 50.0;

    private static final Map<Integer, Double> DISCOUNTS = new HashMap<>();

    static {
        DISCOUNTS.put(1, 0.0);
        DISCOUNTS.put(2, 0.05);
        DISCOUNTS.put(3, 0.10);
        DISCOUNTS.put(4, 0.20);
    }

    public double calculatePrice(int[] basket) {
        if (basket.length == 0) {
            return 0.0;
        }

        Set<Integer> uniqueBooks = new HashSet<>();
        for (int book : basket) {
            uniqueBooks.add(book);
        }

        int distinctCount = uniqueBooks.size();

        // Only handle case where all books are distinct so far
        if (distinctCount == basket.length) {
            double discount = DISCOUNTS.getOrDefault(distinctCount, 0.0);
            double total = basket.length * BOOK_PRICE;
            return total * (1 - discount);
        }

        // fallback for same books
        if (distinctCount == 1) {
            return basket.length * BOOK_PRICE;
        }

        return 0.0; // temporary fallback
    }
}