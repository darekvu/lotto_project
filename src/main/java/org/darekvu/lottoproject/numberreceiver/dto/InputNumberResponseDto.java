package org.darekvu.lottoproject.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record InputNumberResultDto(String message, LocalDateTime drawTime, String ticketId, Set<Integer> numbersFromUser) {
}
