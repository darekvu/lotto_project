package org.darekvu.lottoproject.domain.resultchecker;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class WinnerRetriever {
    private final static int NUMBERS_OF_HITS_WHEN_PLAYER_WON = 3;

    List<Player> retrievePlayers(Set<Ticket> allTicketsByDate, Set<Integer> winningNumbers) {
        return allTicketsByDate.stream().map(ticket -> {
                    Set<Integer> hitNumbers = calculateHits(ticket, winningNumbers);
                    return buildPlayer(ticket, hitNumbers);
                })
                .toList();
    }

    private Player buildPlayer(Ticket ticket, Set<Integer> hitNumbers) {
        Player.PlayerBuilder builder = Player.builder();
        if (isWinner(hitNumbers)) {
            builder.isWinner(true);
        }
        return Player.builder()
                .ticketId(ticket.ticketId())
                .hitNumbers(hitNumbers)
                .numbers(ticket.numbers())
                .drawDate(ticket.drawDate())
                .build();
    }

    private Set<Integer> calculateHits(Ticket ticket, Set<Integer> winningNumbers) {
        return ticket.numbers().stream()
                .filter(winningNumbers::contains)
                .collect(Collectors.toSet());
    }

    private boolean isWinner(Set<Integer> hitNumbers) {
        return hitNumbers.size() >= NUMBERS_OF_HITS_WHEN_PLAYER_WON;
    }

}
