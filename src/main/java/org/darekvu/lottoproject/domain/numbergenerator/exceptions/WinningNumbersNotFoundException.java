package org.darekvu.lottoproject.domain.numbergenerator.exceptions;


public class WinningNumbersNotFoundException extends RuntimeException {
    public WinningNumbersNotFoundException(String message) {
        super(message);
    }
}
