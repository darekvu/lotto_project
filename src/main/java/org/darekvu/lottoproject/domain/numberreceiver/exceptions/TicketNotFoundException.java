package org.darekvu.lottoproject.domain.numberreceiver.exceptions;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message) {
        super(message);
    }
}
