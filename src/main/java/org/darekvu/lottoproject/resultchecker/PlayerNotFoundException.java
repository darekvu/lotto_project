package org.darekvu.lottoproject.resultchecker;

public class PlayerNotFoundException extends RuntimeException {
    PlayerNotFoundException(String message) {
        super(message);
    }
}
