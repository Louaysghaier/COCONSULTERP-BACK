package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Quote;

import java.util.List;

public interface QuoteService {
    List<Quote> retrieveQuotes();

    Quote retrieveQuote(Long id);

    Quote ajouterQuote(Quote quote);

    Quote updateQuote(Quote quote);

    void removeQuote(Long id);

   // List<Expanses> getExpansesForQuote(Long quoteId);

    //List<Assignements> getAssignementsForQuote(Long quoteId);

    Assignements addAssignements(Assignements assignements);

    //List<Meetings> getMeetingsForQuote(Long quoteId);

    Meetings addMeeting(Meetings meeting);
}