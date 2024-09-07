package org.darekvu.lottoproject.numberreceiver;

import java.time.LocalDateTime;
import java.util.Set;

public record TicketDto(String id, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
