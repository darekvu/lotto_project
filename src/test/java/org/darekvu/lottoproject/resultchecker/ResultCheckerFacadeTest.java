package org.darekvu.lottoproject.resultchecker;

import org.darekvu.lottoproject.numbergenerator.WinningNumbersGeneratorFacade;
import org.darekvu.lottoproject.numbergenerator.dto.WinningNumbersDto;
import org.darekvu.lottoproject.numberreceiver.NumberReceiverFacade;
import org.darekvu.lottoproject.numberreceiver.dto.TicketDto;
import org.darekvu.lottoproject.resultchecker.dto.PlayersDto;
import org.darekvu.lottoproject.resultchecker.dto.ResultDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultCheckerFacadeTest {

    private final PlayerRepository playerRepository = new InMemoryPlayerRepositoryTestImpl();


    @Mock
    NumberReceiverFacade numberReceiverFacade;

    @Mock
    WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;

    @Test
    void it_should_generate_all_players_with_success_message() {
//        given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacadeConfiguration()
                .createForTest(numberReceiverFacade, winningNumbersGeneratorFacade, playerRepository);
        //when
        LocalDateTime drawDate = LocalDateTime.of(2024, 9, 7, 12, 0, 0);
        when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(numberReceiverFacade.generateClosestDrawDate()).thenReturn(drawDate);
        when(numberReceiverFacade.retrieveTicketsByDrawDate(drawDate)).thenReturn(List.of(
                TicketDto.builder()
                        .id("001")
                        .numbersFromUser(Set.of(1, 2, 3, 4, 5, 6))
                        .drawDate(drawDate)
                        .build(),
                TicketDto.builder()
                        .id("002")
                        .numbersFromUser(Set.of(1, 4, 65, 3, 81, 8))
                        .drawDate(drawDate)
                        .build(),
                TicketDto.builder()
                        .id("003")
                        .numbersFromUser(Set.of(7, 8, 9, 10, 11, 12))
                        .drawDate(drawDate)
                        .build())
        );

        PlayersDto playersDto = resultCheckerFacade.generateWinners();
        String message = playersDto.message();
        //then
        assertThat(message).isEqualTo("successfully retrieved players");
    }

    @Test
    void it_should_generate_fail_message_when_winningNumbers_equal_null() {
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacadeConfiguration()
                .createForTest(numberReceiverFacade, winningNumbersGeneratorFacade, playerRepository);
        when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(null)
                .build());
        //when

        PlayersDto playersDto = resultCheckerFacade.generateWinners();

        String message = playersDto.message();
        //then
        assertThat(message).isEqualTo("failed to retrieve players");
    }

    @Test
    void it_should_return_correct_result_with_correct_credentials() {
        //given
        ResultCheckerFacade resultCheckerFacade = new ResultCheckerFacadeConfiguration()
                .createForTest(numberReceiverFacade, winningNumbersGeneratorFacade, playerRepository);
        //when
        LocalDateTime drawDate = LocalDateTime.of(2024, 9, 7, 12, 0, 0);
        when(winningNumbersGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .build());
        when(numberReceiverFacade.generateClosestDrawDate()).thenReturn(drawDate);
        String ticketId = "001";
        when(numberReceiverFacade.retrieveTicketsByDrawDate(drawDate)).thenReturn(List.of(
                TicketDto.builder()
                        .id(ticketId)
                        .numbersFromUser(Set.of(1, 2, 3, 4, 5, 6))
                        .drawDate(drawDate)
                        .build(),
                TicketDto.builder()
                        .id("002")
                        .numbersFromUser(Set.of(1, 4, 65, 3, 81, 8))
                        .drawDate(drawDate)
                        .build(),
                TicketDto.builder()
                        .id("003")
                        .numbersFromUser(Set.of(7, 8, 9, 10, 11, 12))
                        .drawDate(drawDate)
                        .build())
        );
        resultCheckerFacade.generateWinners();
        ResultDto resultDto = resultCheckerFacade.findByTicketId(ticketId);
        //when
        ResultDto expectedResult = ResultDto.builder()
                .ticketId(ticketId)
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(drawDate)
                .isWinner(false)
                .build();
        //then
        assertThat(resultDto).isEqualTo(expectedResult);
    }
}