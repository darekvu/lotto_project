package org.darekvu.lottoproject.numberannouncer;

public enum MessageResponse {
    TICKET_ID_DOES_NOT_EXIST_MESSAGE("Given ticket does not exist"),
    WAIT_MESSAGE("Results are being calculated, please wait..."),
    WIN_MESSAGE("Congratulations, you won!"),
    LOSE_MESSAGE("No luck, good luck next time!"),
    ALREADY_CHECKED("You have already checked your ticket, please come back later");

    final String message;

    MessageResponse(String message) {
        this.message = message;
    }
}
