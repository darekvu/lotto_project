package org.darekvu.lottoproject.numbergenerator;

import java.util.Set;

public class WinningNumbersGeneratorTestImpl implements RandomNumberGenerable {
    private final Set<Integer> generatedNumbers;

    WinningNumbersGeneratorTestImpl() {
        this.generatedNumbers = Set.of(1, 2, 3, 4, 5, 6);
    }

    WinningNumbersGeneratorTestImpl(Set<Integer> generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    @Override
    public Set<Integer> generateSixRandomNumbers() {
        return generatedNumbers;
    }

}
