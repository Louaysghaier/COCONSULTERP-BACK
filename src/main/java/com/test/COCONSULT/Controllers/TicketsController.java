package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Entity.Tickets;
import com.test.COCONSULT.Interfaces.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private final TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @GetMapping("getAllTickets")
    public ResponseEntity<List<Tickets>> retrieveTickets() {
        List<Tickets> tickets = ticketsService.retrieveTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/getTicketById/{idTickets}")
    public ResponseEntity<Tickets> retrieveTicket(@PathVariable("idTickets") Long idTickets) {
        Tickets ticket = ticketsService.retrieveTickets(idTickets);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/ajouterTicket")
    public ResponseEntity<Tickets> addTicket(@RequestBody Tickets tickets) {
        Tickets savedTicket = ticketsService.ajouterTicket(tickets);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

    @PutMapping("/updateTicket/{idTickets}")
    public ResponseEntity<Tickets> updateTicket(@PathVariable("idTickets") Long idTickets, @RequestBody Tickets tickets) {
        tickets.setIdTicket(idTickets); // Set the ID from the path variable
        Tickets updatedTicket = ticketsService.updateTicket(tickets);
        if (updatedTicket != null) {
            return ResponseEntity.ok(updatedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteTicket/{idTickets}")
    public ResponseEntity<Void> removeTicket(@PathVariable("idTickets") Long idTickets) {
        ticketsService.removeTicket(idTickets);
        return ResponseEntity.noContent().build();
    }
}
