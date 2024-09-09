package org.darekvu.lottoproject.numbergenerator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryWinningNumbersRepositoryTestImpl implements WinningNumbersRepository {
    private final Map<LocalDateTime, WinningNumbers> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public WinningNumbers save(WinningNumbers winningNumbers) {
        return inMemoryDatabase.put(winningNumbers.date(), winningNumbers);
    }

    @Override
    public Optional<WinningNumbers> findNumbersByDate(LocalDateTime dateTime) {
        return Optional.ofNullable(inMemoryDatabase.get(dateTime));
    }

}
