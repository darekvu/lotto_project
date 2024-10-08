package org.darekvu.lottoproject.domain.numberreceiver.dto;

import lombok.Builder;

@Builder
public record InputNumberResponseDto(TicketDto ticketDto, String message) {
}
