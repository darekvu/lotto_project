package org.darekvu.lottoproject.domain.numberreceiver;

import java.util.UUID;

class TicketIdGenerator implements UniqueIdGenerable {
    @Override
    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
