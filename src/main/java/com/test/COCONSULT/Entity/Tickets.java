package com.test.COCONSULT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.COCONSULT.DTO.TicketPriority;
import com.test.COCONSULT.DTO.TicketStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Tickets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    @Enumerated(EnumType.STRING)
    private TicketPriority ticketPriority;
    private String TicketContent;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "tickets",
            joinColumns = @JoinColumn(name = "tickets_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> activitys;
    @ManyToOne
    private User user;


}
