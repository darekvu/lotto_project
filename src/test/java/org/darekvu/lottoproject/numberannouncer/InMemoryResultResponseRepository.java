package org.darekvu.lottoproject.numberannouncer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryResultResponseRepository implements ResultResponseRepository {
    Map<String, ResultResponse> inMemoryDB = new ConcurrentHashMap<>();

    @Override
    public void save(ResultResponse response) {
        inMemoryDB.put(response.ticketId(), response);
    }

    @Override
    public boolean exists(String ticketId) {
        return inMemoryDB.containsKey(ticketId);
    }

    @Override
    public Optional<ResultResponse> findById(String ticketId) {
        ResultResponse resultResponse = inMemoryDB.get(ticketId);
        return Optional.ofNullable(resultResponse);
    }
}
