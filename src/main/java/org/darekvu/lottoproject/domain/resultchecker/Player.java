package org.darekvu.lottoproject.domain.resultchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
record Player(String ticketId, Set<Integer> numbers,Set<Integer> hitNumbers, LocalDateTime drawDate , boolean isWinner) {
}
