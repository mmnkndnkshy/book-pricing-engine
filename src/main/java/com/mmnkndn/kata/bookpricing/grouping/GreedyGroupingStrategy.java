package com.mmnkndn.kata.bookpricing.grouping;

import com.mmnkndn.kata.bookpricing.domain.Book;

import java.util.*;


public class GreedyGroupingStrategy implements GroupingStrategy {
    @Override
    public List<Integer> group(Map<Book, Integer> counts) {
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
}
