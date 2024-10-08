package org.darekvu.lottoproject.domain.numbergenerator.dto;

import lombok.Builder;

@Builder
public record OneRandomNumberFetcherResponseDto(int number) {
}
