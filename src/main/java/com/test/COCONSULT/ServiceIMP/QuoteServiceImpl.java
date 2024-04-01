package com.test.COCONSULT.ServiceIMP;


import com.test.COCONSULT.Entity.Quote;
import com.test.COCONSULT.Interfaces.QuoteService;
import com.test.COCONSULT.Reposotories.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    QuoteRepository quoteRepository;


    @Override
    public List<Quote> retrieveQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote updateQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Quote ajouterQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Quote retrieveQuote(Long idQuote) {
        return quoteRepository.findById(idQuote).orElse(null);
    }

    @Override
    public void removeQuote(Long idQuote) {
        quoteRepository.deleteById(idQuote);

    }
}