package org.darekvu.lottoproject.numbergenerator;

import org.darekvu.lottoproject.numbergenerator.dto.WinningNumbersDto;
import org.darekvu.lottoproject.numbergenerator.exceptions.WinningNumbersNotFoundException;
import org.darekvu.lottoproject.numberreceiver.NumberReceiverFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WinningNumbersGeneratorFacadeTest {

    @InjectMocks
    WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;

    @Mock
    NumberReceiverFacade numberReceiverFacade;

    InMemoryWinningNumbersRepositoryTestImpl winningNumbersRepo = new InMemoryWinningNumbersRepositoryTestImpl();
    WinningNumbersGeneratorTestImpl winningNumberGenerator = new WinningNumbersGeneratorTestImpl();

    @Test
    void it_should_return_6_winning_numbers() {
        //given
        WinningNumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration()
                .createForTest(winningNumbersRepo, numberReceiverFacade, winningNumberGenerator);
        //when
        when(numberReceiverFacade.generateClosestDrawDate()).thenReturn(LocalDateTime.now());
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.generateWinningNumbers();
        Set<Integer> winningNumbers = winningNumbersDto.winningNumbers();
        //then
        assertThat(winningNumbers.size()).isEqualTo(6);

    }

    @Test
    void it_should_return_6_winning_numbers_in_range() {
        //given
        WinningNumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration()
                .createForTest(winningNumbersRepo, numberReceiverFacade, winningNumberGenerator);
        //when
        when(numberReceiverFacade.generateClosestDrawDate()).thenReturn(LocalDateTime.now());
        WinningNumbersDto winningNumbersDto = numbersGeneratorFacade.generateWinningNumbers();
        //then
        int upperBand = 99;
        int lowerBand = 1;
        boolean result = winningNumbersDto.winningNumbers().stream().allMatch(num -> num <= upperBand && num >= lowerBand);
        assertThat(result).isTrue();
    }

    @Test
    void it_should_throw_exception_when_numbers_out_of_range() {
        Set<Integer> numbersOutOfRange = Set.of(1, 2, 3, 4, 5, 259);
        WinningNumbersGeneratorTestImpl generator = new WinningNumbersGeneratorTestImpl(numbersOutOfRange);
        //given
        WinningNumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration()
                .createForTest(winningNumbersRepo, numberReceiverFacade, generator);
        //when
        when(numberReceiverFacade.generateClosestDrawDate()).thenReturn(LocalDateTime.now());
        assertThrows(IllegalStateException.class, numbersGeneratorFacade::generateWinningNumbers, "Numbers out of range");
    }

    @Test
    void it_should_return_winningNumbers_by_date() {
        //given
        Set<Integer> generatedWinningNumbers = Set.of(1, 2, 3, 4, 5, 6);
        LocalDateTime date = LocalDateTime.of(2024, 9, 7, 12, 0, 0);
        String randomID = UUID.randomUUID().toString();
        WinningNumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration()
                .createForTest(winningNumbersRepo, numberReceiverFacade, winningNumberGenerator);
        WinningNumbers winningNumbers = WinningNumbers.builder()
                .date(date)
                .id(randomID)
                .winningNumbers(generatedWinningNumbers)
                .build();
        winningNumbersRepo.save(winningNumbers);
        //when
        WinningNumbersDto winningNumbersByDateDto = numbersGeneratorFacade.retrieveWinningNumbersByDate(date);
        //then
        WinningNumbersDto expectedWinningNumbersDto = WinningNumbersDto.builder()
                .winningNumbers(generatedWinningNumbers)
                .build();
        assertThat(expectedWinningNumbersDto).isEqualTo(winningNumbersByDateDto);
    }

    @Test
    void it_should_throw_exception_when_winningNumbers_not_found_by_date() {
        LocalDateTime date = LocalDateTime.of(2024, 9, 7, 12, 0, 0);
        //given
        WinningNumbersGeneratorFacade numbersGeneratorFacade = new NumbersGeneratorFacadeConfiguration()
                .createForTest(winningNumbersRepo, numberReceiverFacade, winningNumberGenerator);
        //when
        //then
        assertThrows(WinningNumbersNotFoundException.class, () -> numbersGeneratorFacade.retrieveWinningNumbersByDate(date),
                String.format("Winning numbers for date: %s not found", date));

    }
}