package org.darekvu.lottoproject.numberreceiver;

import org.darekvu.lottoproject.numberreceiver.dto.TicketDto;

public class TicketMapper {

    public static TicketDto mapTicketToDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.id())
                .drawDate(ticket.drawDate())
                .numbersFromUser(ticket.numbersFromUser())
                .build();
    }
}
