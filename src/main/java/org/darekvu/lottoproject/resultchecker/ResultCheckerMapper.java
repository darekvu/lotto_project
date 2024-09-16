package org.darekvu.lottoproject.resultchecker;

import org.darekvu.lottoproject.numberreceiver.dto.TicketDto;
import org.darekvu.lottoproject.resultchecker.dto.ResultDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class ResultCheckerMapper {

    static Set<Ticket> mapTicketDtoToTicket(List<TicketDto> allTicketsByDate) {
        return allTicketsByDate.stream()
                .map(ticketDto -> Ticket.builder()
                        .ticketId(ticketDto.id())
                        .numbers(ticketDto.numbersFromUser())
                        .drawDate(ticketDto.drawDate())
                        .build())
                .collect(Collectors.toSet());
    }

    static List<ResultDto> mapPlayerToResults(List<Player> players) {
        return players.stream()
                .map(player -> ResultDto.builder()
                        .ticketId(player.ticketId())
                        .drawDate(player.drawDate())
                        .hitNumbers(player.hitNumbers())
                        .numbers(player.numbers())
                        .isWinner(player.isWinner())
                        .build())
                .toList();
    }
}
