package org.darekvu.lottoproject.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Optional;

interface WinningNumbersRepository {

    WinningNumbers save(WinningNumbers winningNumbers);

    Optional<WinningNumbers> findNumbersByDate(LocalDateTime dateTime);


}
