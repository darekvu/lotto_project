package org.darekvu.lottoproject.domain.resultchecker;

public class PlayerNotFoundException extends RuntimeException {
    PlayerNotFoundException(String message) {
        super(message);
    }
}
