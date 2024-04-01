package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Entity.Tickets;
import com.test.COCONSULT.Interfaces.TicketsService;
import com.test.COCONSULT.Reposotories.TicketsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TicketsServiceImpl implements TicketsService {

    private final TicketsRepository ticketsRepository;


    @Override
    public List<Tickets> retrieveTickets() {
        return ticketsRepository.findAll();
    }

    @Override
    public Tickets updateTicket(Tickets tickets) {
        return ticketsRepository.save(tickets);
    }

    @Override
    public Tickets ajouterTicket(Tickets tickets) {
        return ticketsRepository.save(tickets);
    }

    @Override
    public Tickets retrieveTickets(Long idticket) {
        return ticketsRepository.findById(idticket).orElse(null);
    }

    @Override
    public void removeTicket(Long idTickets) {
        ticketsRepository.deleteById(idTickets);

    }
}
