package com.mmnkndn.kata.bookpricing.grouping;

import java.util.List;

public class GroupOptimizer {
    public void optimize(List<Integer> groupSizes) {
        while (groupSizes.contains(5) && groupSizes.contains(3)) {
            groupSizes.remove(Integer.valueOf(5));
            groupSizes.remove(Integer.valueOf(3));
            groupSizes.add(4);
            groupSizes.add(4);
        }
    }
}
