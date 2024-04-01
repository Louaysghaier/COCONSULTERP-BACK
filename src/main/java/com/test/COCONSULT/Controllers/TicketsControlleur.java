package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Interfaces.TicketInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ticket")
public class TicketsControlleur {
    @Autowired
    TicketInterface ticketInterface;
}
