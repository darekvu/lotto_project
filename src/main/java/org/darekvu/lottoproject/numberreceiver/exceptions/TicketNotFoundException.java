package org.darekvu.lottoproject.numberreceiver.exceptions;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message) {
        super(message);
    }
}
