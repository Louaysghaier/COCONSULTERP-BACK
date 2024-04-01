package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Quote;
import com.test.COCONSULT.Interfaces.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("getAllQuotes")
    public ResponseEntity<List<Quote>> retrieveQuotes() {
        List<Quote> quotes = quoteService.retrieveQuotes();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/getQuotesById/{idQuote}")
    public ResponseEntity<Quote> retrieveQuote(@PathVariable("idQuote") Long idQuote) {
        Quote quote = quoteService.retrieveQuote(idQuote);
        if (quote != null) {
            return ResponseEntity.ok(quote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterQuote")
    public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {
        Quote savedQuote = quoteService.ajouterQuote(quote);
        return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
    }

    @PutMapping("/updateQuote/{idQuote}")
    public ResponseEntity<Quote> updateQuote(@PathVariable("idQuote") Long idQuote, @RequestBody Quote quote) {
        quote.setIdQuote(idQuote); // Set the ID from the path variable
        Quote updatedQuote = quoteService.updateQuote(quote);
        if (updatedQuote != null) {
            return ResponseEntity.ok(updatedQuote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteQuote/{idQuote}")
    public ResponseEntity<Void> removeQuote(@PathVariable("idQuote") Long idQuote) {
        quoteService.removeQuote(idQuote);
        return ResponseEntity.noContent().build();
    }
}
