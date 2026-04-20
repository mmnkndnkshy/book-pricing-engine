package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;
import com.mmnkndn.kata.bookpricing.api.model.Book;
import com.mmnkndn.kata.bookpricing.grouping.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.mmnkndn.kata.bookpricing.common.BookPricingConstants.BOOK_PRICE;

@RequiredArgsConstructor
@Component("greedyPricingStrategy")
@Primary
public class GreedyPricingStrategy implements PricingStrategy {

    private final DiscountPolicy discountPolicy;
    private final BookCounter bookCounter;
    private final GroupingStrategy groupingStrategy;
    private final GroupOptimizer optimizer;

    @Override
    public double calculateBookPrice(BookPricingRequest request) {

        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            return 0.0;
        }

        Map<Book, Integer> counts = bookCounter.count(request.getItems());

        List<Integer> groupSizes = groupingStrategy.group(counts);

        groupSizes = optimizer.optimize(groupSizes);

        return computeFinalPrice(groupSizes);
    }

    private double computeFinalPrice(List<Integer> groupSizes) {

        double total = groupSizes.stream()
                .mapToDouble(size -> {
                    double discount = discountPolicy.getDiscount(size);
                    return size * BOOK_PRICE * (1 - discount);
                })
                .sum();

        return total;
    }
}