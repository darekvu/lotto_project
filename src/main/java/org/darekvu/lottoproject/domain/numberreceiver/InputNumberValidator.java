package org.darekvu.lottoproject.domain.numberreceiver;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class InputNumberValidator {
    public static final int REQUIRED_SIZE = 6;
    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 99;

    List<ValidationResponse> errors = new LinkedList<>();


    public List<ValidationResponse> validateInputNumbers(Set<Integer> numbersFromUser) {
        if (!isNumbersSizeEqualsSix(numbersFromUser)) {
            errors.add(ValidationResponse.NOT_SIX_NUMBERS_GIVEN);
        }
        if (!isNumberInRange(numbersFromUser)) {
            errors.add(ValidationResponse.NOT_IN_RANGE);
        }
        return errors;
    }

    private boolean isNumbersSizeEqualsSix(Set<Integer> numbersFromUser) {
        return numbersFromUser.size() == REQUIRED_SIZE;
    }

    private boolean isNumberInRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream().allMatch(num -> num >= MIN_RANGE && num <= MAX_RANGE);
    }

    String createErrorResponseMessage() {
        return this.errors.stream().map(error -> error.info)
                .collect(Collectors.joining(", "));
    }
}

