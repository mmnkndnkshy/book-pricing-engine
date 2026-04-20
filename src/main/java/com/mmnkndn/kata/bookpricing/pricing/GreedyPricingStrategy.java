package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.domain.*;
import com.mmnkndn.kata.bookpricing.grouping.*;

import java.util.List;
import java.util.Map;

public class GreedyPricingStrategy implements PricingStrategy {

    private final DiscountPolicy discountPolicy = new DefaultDiscountPolicy();
    private final BookCounter bookCounter = new BookCounter();
    private final GroupingStrategy groupingStrategy = new GreedyGroupingStrategy();
    private final GroupOptimizer optimizer = new GroupOptimizer();

    private static final double BOOK_PRICE = 50.0;

    @Override
    public double calculatePrice(Basket basket) {
        if (basket == null || basket.getBooks().isEmpty()) return 0.0;

        Map<Book, Integer> counts = bookCounter.count(basket.getBooks());
        List<Integer> groupSizes = groupingStrategy.group(counts);

        optimizer.optimize(groupSizes);

        return calculateTotal(groupSizes);
    }

    private double calculateTotal(List<Integer> groupSizes) {
        double total = 0.0;

        for (int size : groupSizes) {
            double discount = discountPolicy.getDiscount(size);
            total += size * BOOK_PRICE * (1 - discount);
        }

        return total;
    }


}