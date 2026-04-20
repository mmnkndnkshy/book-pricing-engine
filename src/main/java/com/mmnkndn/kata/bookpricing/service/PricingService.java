package com.mmnkndn.kata.bookpricing.service;

import com.mmnkndn.kata.bookpricing.api.mapper.BookPricingResponseMapper;
import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;
import com.mmnkndn.kata.bookpricing.api.model.BookPricingResponse;
import com.mmnkndn.kata.bookpricing.pricing.PricingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    private final PricingStrategy pricingStrategy;
    private final BookPricingResponseMapper mapper;

    public PricingService(
            @Qualifier("greedyPricingStrategy") PricingStrategy pricingStrategy,
            BookPricingResponseMapper mapper
    ) {
        this.pricingStrategy = pricingStrategy;
        this.mapper = mapper;
    }

    public BookPricingResponse calculatePrice(BookPricingRequest request) {

        double total = pricingStrategy.calculatePrice(request);

        return mapper.toResponse(request, total);
    }
}