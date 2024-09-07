package org.darekvu.lottoproject.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

interface NumberReceiverRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllTicketsByDate(LocalDateTime date);
}
