package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.Tickets;

import java.util.List;

public interface TicketsService {
    List<Tickets> retrieveTickets();

    Tickets updateTicket(Tickets tickets);

    Tickets ajouterTicket(Tickets tickets);

    Tickets retrieveTickets(Long idticket);

    void removeTicket(Long idTickets);
}