package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.*;

import java.util.List;

public interface QuoteService {
    List<Quote> retrieveQuotes();

    Projets retrieveQuote(Long id);

    Quote ajouterQuote(Quote quote);

    Quote updateQuote(Quote quote);

    void removeQuote(Long id);

   // List<Expanses> getExpansesForQuote(Long quoteId);

   // List<Assignements> getAssignementsForQuote(Long quoteId);

    Assignements addAssignements(Assignements assignements);

    //List<Meetings> getMeetingsForQuote(Long quoteId);

    Meetings addMeeting(Meetings meeting);
}