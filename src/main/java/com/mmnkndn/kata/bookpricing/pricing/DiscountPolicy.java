package com.mmnkndn.kata.bookpricing.pricing;

public interface DiscountPolicy {
    double getDiscount(int groupSize);
}
