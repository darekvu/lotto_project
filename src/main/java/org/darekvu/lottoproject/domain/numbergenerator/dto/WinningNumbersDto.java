package org.darekvu.lottoproject.domain.numbergenerator.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record WinningNumbersDto(Set<Integer> winningNumbers) {
}
