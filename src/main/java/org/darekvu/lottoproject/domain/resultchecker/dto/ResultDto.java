package org.darekvu.lottoproject.domain.resultchecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ResultDto(String ticketId, Set<Integer> numbers, Set<Integer> hitNumbers, LocalDateTime drawDate,
                 boolean isWinner) {

}
