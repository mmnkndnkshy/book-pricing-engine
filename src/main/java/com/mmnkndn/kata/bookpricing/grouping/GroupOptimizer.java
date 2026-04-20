package com.mmnkndn.kata.bookpricing.grouping;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupOptimizer {
    public List<Integer> optimize(List<Integer> groupSizes) {

        List<Integer> optimized = new ArrayList<>(groupSizes);

        while (optimized.contains(5) && optimized.contains(3)) {
            optimized.remove(Integer.valueOf(5));
            optimized.remove(Integer.valueOf(3));
            optimized.add(4);
            optimized.add(4);
        }

        return optimized;
    }
}
