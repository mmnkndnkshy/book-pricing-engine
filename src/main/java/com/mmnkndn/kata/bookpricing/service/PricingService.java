package com.mmnkndn.kata.bookpricing.service;

import com.mmnkndn.kata.bookpricing.domain.Basket;
import com.mmnkndn.kata.bookpricing.domain.Book;

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

    public double calculatePrice(Basket basket) {
        if (basket == null || basket.getBooks().isEmpty()) return 0.0;

        Map<Book, Integer> counts = countBooks(basket.getBooks());
        List<Integer> groupSizes = buildGroups(counts);
        optimizeGroups(groupSizes);

        return calculateTotal(groupSizes);
    }


    private Map<Book, Integer> countBooks(List<Book> basket) {
        Map<Book, Integer> counts = new HashMap<>();

        for (Book book : basket) {
            counts.put(book, counts.getOrDefault(book, 0) + 1);
        }

        return counts;
    }

    private List<Integer> buildGroups(Map<Book, Integer> counts) {
        List<Integer> groupSizes = new ArrayList<>();

        while (!counts.isEmpty()) {
            Set<Book> group = new HashSet<>();

            Iterator<Map.Entry<Book, Integer>> it = counts.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<Book, Integer> entry = it.next();

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

        Map<Integer, Double> discounts = new HashMap<>();
        discounts.put(1, 0.0);
        discounts.put(2, 0.05);
        discounts.put(3, 0.10);
        discounts.put(4, 0.20);
        discounts.put(5, 0.25);

        double total = 0.0;

        for (int size : groupSizes) {
            double discount = discounts.getOrDefault(size, 0.0);
            total += size * 50.0 * (1 - discount);
        }

        return total;
    }
}