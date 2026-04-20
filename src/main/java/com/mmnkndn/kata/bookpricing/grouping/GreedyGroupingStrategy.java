package com.mmnkndn.kata.bookpricing.grouping;

import com.mmnkndn.kata.bookpricing.domain.Book;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GreedyGroupingStrategy implements GroupingStrategy {

    @Override
    public List<Integer> group(Map<Book, Integer> counts) {

        Map<Book, Integer> remaining = new HashMap<>(counts);

        List<Integer> groupSizes = new ArrayList<>();

        while (!remaining.isEmpty()) {
            Set<Book> group = new HashSet<>();

            Iterator<Map.Entry<Book, Integer>> it = remaining.entrySet().iterator();

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