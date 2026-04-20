package com.mmnkndn.kata.bookpricing.api.mapper;

import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;
import com.mmnkndn.kata.bookpricing.domain.Basket;
import com.mmnkndn.kata.bookpricing.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookPricingMapper {

    public static Basket toDomain(BookPricingRequest request) {

        List<Book> books = new ArrayList<>();

        if (request == null || request.getItems() == null) {
            return new Basket(books);
        }

        for (Map.Entry<String, Integer> entry : request.getItems().entrySet()) {

            String bookKey = entry.getKey();
            Integer count = entry.getValue();

            Book book = Book.valueOf(bookKey);

            for (int i = 0; i < count; i++) {
                books.add(book);
            }
        }

        return new Basket(books);
    }
}