package org.darekvu.lottoproject.numberreceiver;

import org.darekvu.lottoproject.AdjustableClock;
import org.darekvu.lottoproject.numberreceiver.dto.InputNumberResponseDto;
import org.darekvu.lottoproject.numberreceiver.dto.TicketDto;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {
    //    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2023, 9, 2, 21, 32, 0)
//            .toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
//
//    Clock clock = Clock.fixed(Instant.parse("2024-09-07T13:00:00Z"), Clock.systemUTC().getZone());
    Clock clock = Clock.fixed(LocalDateTime.of(2024, 9, 7, 16, 30, 0)
            .toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
    private final TicketRepository ticketRepository = new InMemoryTicketRepositoryTestImpl();
    private final UniqueIdGenerable ticketIdGenerator = new UniqueIdGeneratorTestImpl();

    @Test
    void should_return_success_when_input_six_numbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5, 6);
        //when
        InputNumberResponseDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo(ValidationResponse.INPUT_SUCCESS.info);
    }

    @Test
    void should_return_fail_when_input_more_than_six_numbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5, 6, 7);
        //when
        InputNumberResponseDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo(ValidationResponse.NOT_SIX_NUMBERS_GIVEN.info);
    }

    @Test
    void should_return_fail_when_input_less_than_six_numbers() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5);
        //when
        InputNumberResponseDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo(ValidationResponse.NOT_SIX_NUMBERS_GIVEN.info);
    }

    @Test
    void should_return_fail_when_one_of_input_out_of_range() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5, 2000);
        //when
        InputNumberResponseDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo(ValidationResponse.NOT_IN_RANGE.info);
    }

    @Test
    void should_return_correct_unique_id() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //when
        InputNumberResponseDto resultResponse = numberReceiverFacade.inputNumbers(numbersFromUser);
        String id = resultResponse.ticketDto().id();
        //then
        assertThat(id).hasSize(36);
        assertThat(id).isNotNull();
    }

    @Test
    void it_should_return_correct_draw_date() {
        Clock clock = Clock.fixed(LocalDateTime.of(2024, 9, 7, 16, 30, 0)
                .toInstant(ZoneOffset.UTC), ZoneId.of("Europe/London"));
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        //when
        LocalDateTime drawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().drawDate();
        //then
        LocalDateTime expectedDrawDate = LocalDateTime.of(2024, 9, 14, 12, 0, 0);
        assertThat(drawDate).isEqualTo(expectedDrawDate);
    }

    @Test
    void it_should_return_the_same_day_Saturday_if_before_noon() {
        Clock clock = Clock.fixed(Instant.parse("2024-09-07T11:30:00Z"), Clock.systemUTC().getZone());
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        //when
        LocalDateTime drawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().drawDate();
        //then
        LocalDateTime expectedDrawDate = LocalDateTime.of(2024, 9, 7, 12, 0, 0);
        assertThat(drawDate).isEqualTo(expectedDrawDate);
    }


    @Test
    void it_should_return_tickets_associated_with_particular_draw_date() {
        Instant fixedInstant = LocalDateTime.of(2022, 12, 15, 12, 0, 0).toInstant(ZoneOffset.UTC);
        ZoneId zone = ZoneId.of("Europe/London");
        AdjustableClock clock = new AdjustableClock(fixedInstant, zone);
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //given
        Set<Integer> numbersFromUser1 = Set.of(1, 2, 3, 4, 5, 6);
        Set<Integer> numbersFromUser2 = Set.of(7, 8, 9, 10, 11, 12);
        Set<Integer> numbersFromUser3 = Set.of(13, 14, 15, 16, 17, 18);
        InputNumberResponseDto inputNumberResponseDto1 = numberReceiverFacade.inputNumbers(numbersFromUser1);
        clock.plusDays(1);
        InputNumberResponseDto inputNumberResponseDto2 = numberReceiverFacade.inputNumbers(numbersFromUser2);
        clock.plusDays(1);
        InputNumberResponseDto inputNumberResponseDto3 = numberReceiverFacade.inputNumbers(numbersFromUser3);
        clock.plusDays(1);
        TicketDto ticketDto1 = inputNumberResponseDto1.ticketDto();
        TicketDto ticketDto2 = inputNumberResponseDto2.ticketDto();
        LocalDateTime drawDate = inputNumberResponseDto1.ticketDto().drawDate();
        List<TicketDto> allTicketsByDate = numberReceiverFacade.retrieveTicketsByDrawDate(drawDate);
        assertThat(allTicketsByDate).containsOnly(ticketDto1, ticketDto2);
    }

    @Test
    void it_should_return_empty_collection_if_thggere_are_no_tickets() {
        //given
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacadeConfiguration()
                .createForTest(ticketRepository, clock, ticketIdGenerator);
        //when
        LocalDateTime drawDate = LocalDateTime.now();
        List<TicketDto> ticketDtos = numberReceiverFacade.retrieveTicketsByDrawDate(drawDate);
        //then
        assertThat(ticketDtos).isEmpty();
    }
}

