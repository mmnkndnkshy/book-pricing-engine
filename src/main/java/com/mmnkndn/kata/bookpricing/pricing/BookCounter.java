package com.mmnkndn.kata.bookpricing.pricing;

import com.mmnkndn.kata.bookpricing.api.model.Book;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class BookCounter {

    public Map<Book, Integer> count(Map<String, Integer> items) {

        if (items == null || items.isEmpty()) {
            return Map.of();
        }

        Map<Book, Integer> counts = new EnumMap<>(Book.class);

        items.forEach((key, value) -> {

            Book book = Book.valueOf(key); // enum conversion

            counts.put(book, value);
        });

        return counts;
    }
}