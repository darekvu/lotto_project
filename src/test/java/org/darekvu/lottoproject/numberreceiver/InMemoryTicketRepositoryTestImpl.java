package org.darekvu.lottoproject.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryNumberReceiverRepositoryTestImpl implements NumberReceiverRepository {
    Map<String, Ticket> InMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public Ticket save(Ticket ticket) {
        InMemoryDatabase.put(ticket.id(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllTicketsByDate(LocalDateTime date) {
        return InMemoryDatabase.values().stream()
                .filter(ticket -> ticket.drawDate().equals(date))
                .toList();
    }
}
