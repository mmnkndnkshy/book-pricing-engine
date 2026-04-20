package com.mmnkndn.kata.bookpricing.service;

import com.mmnkndn.kata.bookpricing.api.mapper.BookPricingResponseMapper;
import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;
import com.mmnkndn.kata.bookpricing.api.model.BookPricingResponse;
import com.mmnkndn.kata.bookpricing.pricing.PricingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookPricingService {

    @Qualifier("greedyPricingStrategy")
    private final PricingStrategy pricingStrategy;

    private final BookPricingResponseMapper mapper;

    public BookPricingResponse calculateBookPrice(BookPricingRequest request) {
        double total = pricingStrategy.calculateBookPrice(request);
        return mapper.toResponse(request, total);
    }
}