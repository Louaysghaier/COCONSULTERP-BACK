package com.test.COCONSULT.ServiceIMP;

import com.test.COCONSULT.Interfaces.TicketInterface;
import com.test.COCONSULT.Reposotories.TicketsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TicketServiceImp implements TicketInterface {
    @Autowired
    TicketsRepository ticketRepository;
}
