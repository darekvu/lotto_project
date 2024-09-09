package org.darekvu.lottoproject.numberreceiver;

import java.time.Clock;

public class NumberReceiverFacadeConfiguration {
    NumberReceiverFacade createForTest(TicketRepository ticketRepository, Clock clock, UniqueIdGenerable ticketIdGenerator) {
        InputNumberValidator inputNumberValidator = new InputNumberValidator();
        DrawDateGenerator drawDateGenerator = new DrawDateGenerator(clock);
        return new NumberReceiverFacade(inputNumberValidator, ticketRepository, drawDateGenerator, ticketIdGenerator);
    }
}
