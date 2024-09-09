package org.darekvu.lottoproject.numberreceiver.dto;

import lombok.Builder;

@Builder
public record InputNumberResponseDto(TicketDto ticketDto, String message) {
}
