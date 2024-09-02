package org.darekvu.lottoproject.numberreceiver;

import java.util.Set;

class InputNumberValidator {
    public static final int REQUIRED_SIZE = 6;
    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 99;

    public boolean validateInputNumbers(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() == REQUIRED_SIZE && numbersFromUser.stream().allMatch(num -> num >= MIN_RANGE && num <= MAX_RANGE);
    }
}
