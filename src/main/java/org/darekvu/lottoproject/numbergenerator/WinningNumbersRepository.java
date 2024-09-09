package org.darekvu.lottoproject.numbergenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface WinningNumbersRepository {

    WinningNumbers save(WinningNumbers winningNumbers);

    Optional<WinningNumbers> findNumbersByDate(LocalDateTime dateTime);


}
