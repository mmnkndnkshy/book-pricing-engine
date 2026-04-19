package com.mmnkndn.kata.bookpricing.service;

import java.util.*;

public class PricingService {

    private static final double BOOK_PRICE = 50.0;

    private static final Map<Integer, Double> DISCOUNTS = new HashMap<>();

    static {
        DISCOUNTS.put(1, 0.0);
        DISCOUNTS.put(2, 0.05);
        DISCOUNTS.put(3, 0.10);
        DISCOUNTS.put(4, 0.20);
        DISCOUNTS.put(5, 0.25);
    }

    public double calculatePrice(int[] basket) {
        if (basket.length == 0) return 0.0;

        // Count occurrences
        Map<Integer, Integer> counts = new HashMap<>();
        for (int book : basket) {
            counts.put(book, counts.getOrDefault(book, 0) + 1);
        }

        double total = 0.0;

        // Greedy grouping
        while (!counts.isEmpty()) {
            Set<Integer> group = new HashSet<>();

            Iterator<Map.Entry<Integer, Integer>> it = counts.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> entry = it.next();
                group.add(entry.getKey());

                // reduce count
                if (entry.getValue() == 1) {
                    it.remove();
                } else {
                    entry.setValue(entry.getValue() - 1);
                }
            }

            int size = group.size();
            double discount = DISCOUNTS.getOrDefault(size, 0.0);
            double groupPrice = size * BOOK_PRICE * (1 - discount);

            total += groupPrice;
        }

        return total;
    }
}