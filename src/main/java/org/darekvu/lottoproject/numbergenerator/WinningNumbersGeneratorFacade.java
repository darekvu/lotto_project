package org.darekvu.lottoproject.numbergenerator;

import lombok.RequiredArgsConstructor;
import org.darekvu.lottoproject.numbergenerator.dto.WinningNumbersDto;
import org.darekvu.lottoproject.numbergenerator.exceptions.WinningNumbersNotFoundException;
import org.darekvu.lottoproject.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class WinningNumbersGeneratorFacade {
    private final RandomNumberGenerable winningNumberGenerator;
    private final WinningNumbersValidator winningNumbersValidator;
    private final WinningNumbersRepository winningNumbersRepository;
    private final NumberReceiverFacade numberReceiverFacade;

    public WinningNumbersDto generateWinningNumbers() {
        LocalDateTime closestDrawDate = numberReceiverFacade.generateClosestDrawDate();
        Set<Integer> winningNumbers = winningNumberGenerator.generateSixRandomNumbers();

        winningNumbersValidator.validate(winningNumbers);

        winningNumbersRepository.save(WinningNumbers.builder()
                .winningNumbers(winningNumbers)
                .date(closestDrawDate)
                .build());

        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbers)
                .build();
    }

    //method for retrieving winning numbers by date
    //
    public WinningNumbersDto retrieveWinningNumbersByDate(LocalDateTime date) {
        WinningNumbers winningNumbersByDate = winningNumbersRepository.findNumbersByDate(date)
                .orElseThrow(() -> new WinningNumbersNotFoundException(String.format("Winning numbers for date: %s not found", date)));
        return WinningNumbersDto.builder()
                .winningNumbers(winningNumbersByDate.winningNumbers())
                .build();
    }
}
