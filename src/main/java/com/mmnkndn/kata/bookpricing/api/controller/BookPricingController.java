package com.mmnkndn.kata.bookpricing.api.controller;

import com.mmnkndn.kata.bookpricing.api.model.BookPricingRequest;
import com.mmnkndn.kata.bookpricing.api.model.BookPricingResponse;
import com.mmnkndn.kata.bookpricing.service.BookPricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pricing/v1")
public class BookPricingController {

    private final BookPricingService pricingService;

    @PostMapping("/calculatePricing")
    public BookPricingResponse calculatePricing(@RequestBody BookPricingRequest request) {
        return pricingService.calculateBookPrice(request);
    }
}