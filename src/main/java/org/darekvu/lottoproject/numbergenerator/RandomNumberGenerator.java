package org.darekvu.lottoproject.numbergenerator;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class RandomNumberGenerator implements RandomNumberGenerable {
    private final int LOWER_BAND = 1;
    private final int UPPER_BAND = 99;
    private final int NUMBER_BOUND = (UPPER_BAND - LOWER_BAND) + 1;
    private final int REQUIRED_SIZE = 6;

    public Set<Integer> generateSixRandomNumbers() {
        Set<Integer> winningNumbers = new HashSet<>();
        for (int i = 0; i < REQUIRED_SIZE; i++) {
            int randomNumber = generateRandomNumber();
            winningNumbers.add(randomNumber);
        }
        return winningNumbers;
    }

    private int generateRandomNumber() {
        Random random = new SecureRandom();
        return random.nextInt(NUMBER_BOUND);
    }
}
