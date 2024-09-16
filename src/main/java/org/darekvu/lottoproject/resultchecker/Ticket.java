package org.darekvu.lottoproject.resultchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record Ticket(String ticketId, Set<Integer> numbers, LocalDateTime drawDate) {
}
