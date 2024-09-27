package org.darekvu.lottoproject.numberannouncer;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record ResultResponse(String ticketId, Set<Integer> numbers, Set<Integer> hitNumbers, LocalDateTime drawDate,
                      boolean isWinner) {
}
