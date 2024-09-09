package org.darekvu.lottoproject.numbergenerator;

import java.util.Set;

class WinningNumbersValidator {
    private final int LOWER_BAND = 0;
    private final int UPPER_BAND = 99;


    Set<Integer> validate(Set<Integer> winningNumbers) {
        if (outOfRange(winningNumbers)) {
            throw new IllegalStateException("Numbers out of range");
        }
        return winningNumbers;
    }

    private boolean outOfRange(Set<Integer> winningNumbers) {
        return winningNumbers.stream().anyMatch(num -> num < LOWER_BAND || num > UPPER_BAND);
    }
}
