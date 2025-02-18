package org.darekvu.lottoproject.domain.numbergenerator;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record WinningNumbers(String id, Set<Integer> winningNumbers, LocalDateTime date) {
}
