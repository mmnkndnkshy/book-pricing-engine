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

        Map<Integer, Integer> counts = new HashMap<>();
        for (int book : basket) {
            counts.put(book, counts.getOrDefault(book, 0) + 1);
        }

        List<Integer> groupSizes = new ArrayList<>();

        // Step 1: Greedy grouping → collect group sizes
        while (!counts.isEmpty()) {
            Set<Integer> group = new HashSet<>();

            Iterator<Map.Entry<Integer, Integer>> it = counts.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> entry = it.next();
                group.add(entry.getKey());

                if (entry.getValue() == 1) {
                    it.remove();
                } else {
                    entry.setValue(entry.getValue() - 1);
                }
            }

            groupSizes.add(group.size());
        }

        // Step 2: Fix (5 + 3) → (4 + 4)
        while (groupSizes.contains(5) && groupSizes.contains(3)) {
            groupSizes.remove(Integer.valueOf(5));
            groupSizes.remove(Integer.valueOf(3));
            groupSizes.add(4);
            groupSizes.add(4);
        }

        // Step 3: Calculate total
        double total = 0.0;
        for (int size : groupSizes) {
            double discount = DISCOUNTS.getOrDefault(size, 0.0);
            total += size * BOOK_PRICE * (1 - discount);
        }

        return total;
    }
}