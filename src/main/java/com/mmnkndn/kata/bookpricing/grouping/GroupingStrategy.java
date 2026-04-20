package com.mmnkndn.kata.bookpricing.grouping;

import com.mmnkndn.kata.bookpricing.domain.Book;

import java.util.List;
import java.util.Map;

public interface GroupingStrategy {
    List<Integer> group(Map<Book, Integer> counts);
}
