package org.darekvu.lottoproject.domain.numberreceiver;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

//Encja do bazy
@Builder
record Ticket(String id, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
