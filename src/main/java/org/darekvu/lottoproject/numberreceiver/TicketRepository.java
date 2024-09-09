package org.darekvu.lottoproject.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface TicketRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDate(LocalDateTime date);

    Optional<Ticket> findTicketById(String ticketId);
}
