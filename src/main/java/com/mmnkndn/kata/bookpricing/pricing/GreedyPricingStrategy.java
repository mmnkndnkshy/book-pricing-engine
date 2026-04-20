package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.domain.*;
import com.mmnkndn.kata.bookpricing.grouping.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("greedyPricingStrategy")
public class GreedyPricingStrategy implements PricingStrategy {

    private final DiscountPolicy discountPolicy;
    private final BookCounter bookCounter;
    private final GroupingStrategy groupingStrategy;
    private final GroupOptimizer optimizer;

    private static final double BOOK_PRICE = 50.0;

    public GreedyPricingStrategy(
            DiscountPolicy discountPolicy,
            BookCounter bookCounter,
            GroupingStrategy groupingStrategy,
            GroupOptimizer optimizer
    ) {
        this.discountPolicy = discountPolicy;
        this.bookCounter = bookCounter;
        this.groupingStrategy = groupingStrategy;
        this.optimizer = optimizer;
    }

    @Override
    public double calculatePrice(Basket basket) {
        if (basket == null || basket.getBooks().isEmpty()) return 0.0;

        Map<Book, Integer> counts = bookCounter.count(basket.getBooks());
        List<Integer> groupSizes = groupingStrategy.group(counts);

        groupSizes = optimizer.optimize(groupSizes);

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