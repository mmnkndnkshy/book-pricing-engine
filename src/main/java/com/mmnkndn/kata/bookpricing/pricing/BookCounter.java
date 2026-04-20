package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.domain.Book;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class BookCounter {

    public Map<Book, Integer> count(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return Map.of();
        }

        Map<Book, Integer> counts = new EnumMap<>(Book.class);

        for (Book book : books) {
            counts.put(book, counts.getOrDefault(book, 0) + 1);
        }

        return counts;
    }
}