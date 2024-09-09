package org.darekvu.lottoproject.numberreceiver;

import java.util.UUID;

class UniqueIdGeneratorTestImpl implements UniqueIdGenerable {

    @Override
    public String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
}
