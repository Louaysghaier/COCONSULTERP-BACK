package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Assignements;
import com.test.COCONSULT.Entity.Meetings;
import com.test.COCONSULT.Entity.Quote;
import com.test.COCONSULT.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {


    private QuoteService quoteService;
    @Autowired
    private ProjetsService projetsService;
    private ExpansesService expansesService;
    private AssignementsService assignementsService;
    private MeetingsService meetingsService;

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

    /*public ResponseEntity<List<Expanses>> getExpansesForQuote(@PathVariable("id") Long idQuote) {
        List<Expanses> expanses = quoteService.getExpansesForQuote(idQuote);
        return ResponseEntity.ok(expanses);
    }*/

/*@GetMapping("/{id}/assignements")
    public ResponseEntity<List<Assignements>> getAssignementsForQuote(@PathVariable("id") Long idQuote) {
        List<Assignements> assignements = quoteService.getAssignementsForQuote(idQuote);
        return ResponseEntity.ok(assignements);
    }*/

    @PostMapping("/{id}/assignements")
    public ResponseEntity<Assignements> createAssignementForQuote(@PathVariable("id") Long idQuote, @RequestBody Assignements assignements) {
        // Assurez-vous de définir la citation pour l'assignement
        assignements.setQuote(quoteService.retrieveQuote(idQuote));
        Assignements savedAssignement = quoteService.addAssignements(assignements);
        return new ResponseEntity<>(savedAssignement, HttpStatus.CREATED);
    }

   /* @GetMapping("/{id}/meetings")
    public ResponseEntity<List<Meetings>> getMeetingsForQuote(@PathVariable("id") Long idQuote) {
        List<Meetings> meetings = quoteService.getMeetingsForQuote(idQuote);
        return ResponseEntity.ok(meetings);
    }*/

    @PostMapping("/{id}/meetings")
    public ResponseEntity<Meetings> createMeetingForQuote(@PathVariable("id") Long idQuote, @RequestBody Meetings meeting) {
        // Assurez-vous de définir la citation pour la réunion
        meeting.setQuote(quoteService.retrieveQuote(idQuote));
        Meetings savedMeeting = quoteService.addMeeting(meeting);
        return new ResponseEntity<>(savedMeeting, HttpStatus.CREATED);
    }
}
