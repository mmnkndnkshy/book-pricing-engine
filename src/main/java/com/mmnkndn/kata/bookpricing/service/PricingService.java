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

        Map<Integer, Integer> counts = countBooks(basket);
        List<Integer> groupSizes = buildGroups(counts);
        optimizeGroups(groupSizes);

        return calculateTotal(groupSizes);
    }

    private Map<Integer, Integer> countBooks(int[] basket) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int book : basket) {
            counts.put(book, counts.getOrDefault(book, 0) + 1);
        }
        return counts;
    }

    private List<Integer> buildGroups(Map<Integer, Integer> counts) {
        List<Integer> groupSizes = new ArrayList<>();

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

        return groupSizes;
    }

    private void optimizeGroups(List<Integer> groupSizes) {
        while (groupSizes.contains(5) && groupSizes.contains(3)) {
            groupSizes.remove(Integer.valueOf(5));
            groupSizes.remove(Integer.valueOf(3));
            groupSizes.add(4);
            groupSizes.add(4);
        }
    }

    private double calculateTotal(List<Integer> groupSizes) {
        double total = 0.0;
        for (int size : groupSizes) {
            double discount = DISCOUNTS.getOrDefault(size, 0.0);
            total += size * BOOK_PRICE * (1 - discount);
        }
        return total;
    }
}