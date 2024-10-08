package org.darekvu.lottoproject.domain.resultchecker;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPlayerRepositoryTestImpl implements PlayerRepository {
    private Map<String, Player> inMemoryDB = new ConcurrentHashMap<>();

    @Override
    public List<Player> saveAll(List<Player> players) {
        players.forEach(player -> inMemoryDB.put(player.ticketId(), player));
        return players;
    }

    @Override
    public Optional<Player> findById(String ticketId) {
        return Optional.ofNullable(inMemoryDB.get(ticketId));
    }
}
