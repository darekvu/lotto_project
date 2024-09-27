package org.darekvu.lottoproject.numberannouncer.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
public record ResultResponseDto(String ticketId, Set<Integer> numbers, Set<Integer> hitNumbers, LocalDateTime drawDate,
                                boolean isWinner) {
}
