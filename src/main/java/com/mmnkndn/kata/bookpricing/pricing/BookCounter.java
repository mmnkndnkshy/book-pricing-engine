package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.domain.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookCounter {
    public Map<Book, Integer> count(List<Book> books) {
        Map<Book, Integer> counts = new HashMap<>();

        for (Book book : books) {
            counts.put(book, counts.getOrDefault(book, 0) + 1);
        }

        return counts;
    }
}
