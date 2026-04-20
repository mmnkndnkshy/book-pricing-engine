package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("dynamicProgrammingPricingStrategy")
public class DynamicProgrammingPricingStrategy implements PricingStrategy {

    private final DiscountPolicy discountPolicy;

    public DynamicProgrammingPricingStrategy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    private static final double BOOK_PRICE = 50.0;

    private final Map<String, Double> memo = new HashMap<>();

    @Override
    public double calculatePrice(Basket basket) {
        int[] counts = toCounts(basket.getBooks());
        return dp(counts);
    }

    private double dp(int[] counts) {
        String key = Arrays.toString(counts);

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        boolean empty = true;
        for (int c : counts) {
            if (c != 0) {
                empty = false;
                break;
            }
        }

        if (empty) return 0.0;

        double minPrice = Double.MAX_VALUE;

        for (int size = 1; size <= 5; size++) {

            List<Integer> indices = pickDistinct(counts, size);

            if (indices.size() != size) continue;

            for (int i : indices) counts[i]--;

            double price = size * BOOK_PRICE * (1 - discountPolicy.getDiscount(size))
                    + dp(counts);

            minPrice = Math.min(minPrice, price);

            for (int i : indices) counts[i]++;
        }

        memo.put(key, minPrice);
        return minPrice;
    }

    private int[] toCounts(List<Book> books) {
        int[] counts = new int[5];

        for (Book b : books) {
            counts[b.ordinal()]++;
        }

        return counts;
    }

    private List<Integer> pickDistinct(int[] counts, int size) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < counts.length && result.size() < size; i++) {
            if (counts[i] > 0) {
                result.add(i);
            }
        }

        return result;
    }


}