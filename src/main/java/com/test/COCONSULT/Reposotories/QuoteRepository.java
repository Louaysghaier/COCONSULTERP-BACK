package com.test.COCONSULT.Reposotories;

import com.test.COCONSULT.Entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findByMontantGreaterThan(double montant);

    List<Quote> findByCreationDateBefore(LocalDate date);

    List<Quote> findByExpireDateAfter(LocalDate date);

    List<Quote> findByDescriptionContaining(String keyword);
    List<Quote> findByIdQuote(Long quoteId);

}