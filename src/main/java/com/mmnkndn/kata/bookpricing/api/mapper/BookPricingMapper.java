package com.mmnkndn.kata.bookpricing.api.mapper;

import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;
import com.mmnkndn.kata.bookpricing.domain.Basket;
import com.mmnkndn.kata.bookpricing.domain.Book;

import java.util.stream.Collectors;

public class BookPricingMapper {

    public static Basket toDomain(BookPricingRequest request) {
        return new Basket(
                request.getBooks()
                        .stream()
                        .map(b -> Book.valueOf(b.name()))
                        .collect(Collectors.toList())
        );
    }
}