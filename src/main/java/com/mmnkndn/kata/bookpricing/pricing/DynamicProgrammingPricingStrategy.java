package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.api.model.Book;
import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component("dynamicProgrammingPricingStrategy")
public class DynamicProgrammingPricingStrategy implements PricingStrategy {

    private final DiscountPolicy discountPolicy;

    private static final double BOOK_PRICE = 50.0;

    private final Map<String, Double> memo = new HashMap<>();

    @Override
    public double calculatePrice(BookPricingRequest request) {

        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            return 0.0;
        }

        int[] counts = toCounts(request.getItems());

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

            for (int i : indices) {
                counts[i]--;
            }

            double price = size * BOOK_PRICE * (1 - discountPolicy.getDiscount(size))
                    + dp(counts);

            minPrice = Math.min(minPrice, price);

            for (int i : indices) {
                counts[i]++;
            }
        }

        memo.put(key, minPrice);
        return minPrice;
    }

    private int[] toCounts(Map<String, Integer> items) {

        int[] counts = new int[5];

        for (Map.Entry<String, Integer> entry : items.entrySet()) {

            Book book = Book.valueOf(entry.getKey());

            counts[book.ordinal()] = entry.getValue();
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