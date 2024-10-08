package org.darekvu.lottoproject.domain.numberannouncer;

import org.darekvu.lottoproject.domain.numberannouncer.dto.ResultAnnouncerResponseDto;
import org.darekvu.lottoproject.domain.resultchecker.ResultCheckerFacade;
import org.darekvu.lottoproject.domain.resultchecker.dto.ResultDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NumberAnnouncerFacadeTest {
    @Mock
    ResultCheckerFacade resultCheckerFacade;

    @Test
    void it_should_return_lose_response_if_ticket_is_losing_ticket() {

        //given
        InMemoryResultResponseRepository inMemoryResultResponseRepository = new InMemoryResultResponseRepository();
        NumberAnnouncerFacade numberAnnouncerFacade = new ResultAnnouncerConfiguration()
                .createForTest(resultCheckerFacade, inMemoryResultResponseRepository, Clock.systemUTC());
        //when

        String ticketId = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2024, 9, 7, 16, 30, 0);
        ResultDto resultDto = new ResultDto(
                ticketId,
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(1, 2),
                drawDate,
                false
        );
        when(resultCheckerFacade.findByTicketId(resultDto.ticketId())).thenReturn(resultDto);
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = numberAnnouncerFacade.checkResult(resultDto.ticketId());
        //then
        assertThat(resultAnnouncerResponseDto.message()).isEqualTo(MessageResponse.LOSE_MESSAGE.message);
    }

    @Test
    void it_should_return_winning_response_if_ticket_is_winning_ticket() {
        //given
        InMemoryResultResponseRepository inMemoryResultResponseRepository = new InMemoryResultResponseRepository();
        NumberAnnouncerFacade numberAnnouncerFacade = new ResultAnnouncerConfiguration()
                .createForTest(resultCheckerFacade, inMemoryResultResponseRepository, Clock.systemUTC());
        //when

        String ticketId = UUID.randomUUID().toString();
        LocalDateTime drawDate = LocalDateTime.of(2024, 9, 7, 16, 30, 0);
        ResultDto resultDto = new ResultDto(
                ticketId,
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(1, 2, 3, 4, 5),
                drawDate,
                true
        );
        when(resultCheckerFacade.findByTicketId(resultDto.ticketId())).thenReturn(resultDto);
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = numberAnnouncerFacade.checkResult(resultDto.ticketId());
        //then
        assertThat(resultAnnouncerResponseDto.message()).isEqualTo(MessageResponse.WIN_MESSAGE.message);
    }

    @Test
    void it_should_return_wait_response_if_date_is_before_announcement_time() {
        LocalDateTime drawDate = LocalDateTime.of(2024, 9, 7, 10, 30, 0);
        Clock clock = Clock.fixed(LocalDateTime.of(2024, 9, 7, 10, 30, 0)
                .toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        //given
        InMemoryResultResponseRepository inMemoryResultResponseRepository = new InMemoryResultResponseRepository();
        NumberAnnouncerFacade numberAnnouncerFacade = new ResultAnnouncerConfiguration()
                .createForTest(resultCheckerFacade, inMemoryResultResponseRepository, clock);
        //when
        String ticketId = UUID.randomUUID().toString();
        ResultDto resultDto = new ResultDto(
                ticketId,
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(1, 2, 3, 4, 5),
                drawDate,
                true
        );
        ResultResponse resultResponse = new ResultResponse(
                ticketId,
                Set.of(1, 2, 3, 4, 5, 6),
                Set.of(1, 2, 3, 4, 5),
                drawDate,
                true
        );

        when(resultCheckerFacade.findByTicketId(resultDto.ticketId())).thenReturn(resultDto);
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = numberAnnouncerFacade.checkResult(resultDto.ticketId());
        //then
        assertThat(resultAnnouncerResponseDto.message()).isEqualTo(MessageResponse.WAIT_MESSAGE.message);
    }

    @Test
    void it_should_return_response_does_not_exist() {
        //given
        InMemoryResultResponseRepository inMemoryResultResponseRepository = new InMemoryResultResponseRepository();
        NumberAnnouncerFacade numberAnnouncerFacade = new ResultAnnouncerConfiguration()
                .createForTest(resultCheckerFacade, inMemoryResultResponseRepository, Clock.systemUTC());
        String ticketId = UUID.randomUUID().toString();
        //when
        when(resultCheckerFacade.findByTicketId(ticketId)).thenReturn(null);
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = numberAnnouncerFacade.checkResult(ticketId);
        //then
        assertThat(resultAnnouncerResponseDto.message()).isEqualTo(MessageResponse.TICKET_ID_DOES_NOT_EXIST_MESSAGE.message);
    }
}