package org.darekvu.lottoproject.numbergenerator;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
class RandomNumberGenerator implements RandomNumberGenerable {
    private final int LOWER_BAND = 1;
    private final int UPPER_BAND = 99;
    private final int NUMBER_BOUND = (UPPER_BAND - LOWER_BAND) + 1;
    private final int REQUIRED_SIZE = 6;
    private final OneRandomNumberFetcher client;

    public Set<Integer> generateSixRandomNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        for (int i = 0; i < REQUIRED_SIZE; i++) {
            var randomNumberDto = client.retrieveOneRandomNumber(LOWER_BAND, UPPER_BAND);
            winningNumbers.add(randomNumberDto.number());
        }
        return winningNumbers;
    }

}
