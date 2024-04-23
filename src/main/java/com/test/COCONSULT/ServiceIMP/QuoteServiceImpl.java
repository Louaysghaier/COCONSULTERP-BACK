package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.*;
import com.test.COCONSULT.Interfaces.QuoteService;
import com.test.COCONSULT.Reposotories.AssignementsRepository;
import com.test.COCONSULT.Reposotories.ExpansesRepository;
import com.test.COCONSULT.Reposotories.MeetingsRepository;
import com.test.COCONSULT.Reposotories.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    QuoteRepository quoteRepository;

    private ExpansesRepository expansesRepository;
    private AssignementsRepository assignementsRepository;
    private  MeetingsRepository meetingsRepository;

    @Override
    public List<Quote> retrieveQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Projets retrieveQuote(Long idQuote) {
        return Objects.requireNonNull(quoteRepository.findById(idQuote).orElse(null)).getProjets();
    }

    @Override
    public Quote ajouterQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Quote updateQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public void removeQuote(Long idQuote) {
        quoteRepository.deleteById(idQuote);
    }

   /* @Override
    public List<Expanses> getExpansesForQuote(Long quoteId) {
        return expansesRepository.findByQuoteId(quoteId);
    }*/

/*@Override
    public List<Assignements> getAssignementsForQuote(Long quoteId) {
        return assignementsRepository.findByQuoteIdQuote(quoteId);
    }*/

    @Override
    public Assignements addAssignements(Assignements assignements) {
        return assignementsRepository.save(assignements);
    }

    /*@Override
    public List<Meetings> getMeetingsForQuote(Long quoteId) {
        return meetingsRepository.findByQuoteId(quoteId);
    }*/

    @Override
    public Meetings addMeeting(Meetings meeting) {
        return meetingsRepository.save(meeting);
    }
}