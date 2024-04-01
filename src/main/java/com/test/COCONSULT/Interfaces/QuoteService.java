package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Quote;

import java.util.List;

public interface QuoteService {
    List<Quote> retrieveQuotes();

    Quote updateQuote(Quote quote);

    Quote ajouterQuote(Quote quote);

    Quote retrieveQuote(Long idQuote);

    void removeQuote(Long idQuote);
}