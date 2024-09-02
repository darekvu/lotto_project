package org.darekvu.lottoproject.numberreceiver;

import org.darekvu.lottoproject.numberreceiver.dto.InputNumberResultDto;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(new InputNumberValidator());

    @Test
    void should_return_success_when_input_six_numbers() {
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5, 6);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo("success");
    }

    @Test
    void should_return_fail_when_input_more_than_six_numbers() {
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5, 6, 7);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    void should_return_fail_when_input_less_than_six_numbers() {
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    void should_return_fail_when_one_of_input_out_of_range() {
        //given
        Set<Integer> nums = Set.of(1, 2, 3, 4, 5, 2000);
        //when
        InputNumberResultDto result = numberReceiverFacade.inputNumbers(nums);
        //then
        assertThat(result.message()).isEqualTo("failed");
    }
}