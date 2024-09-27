package org.darekvu.lottoproject.numberannouncer;

import org.darekvu.lottoproject.numberannouncer.dto.ResultResponseDto;
import org.darekvu.lottoproject.resultchecker.dto.ResultDto;

public class ResponseMapper {

    public static ResultResponseDto mapToResultResponseDto(ResultResponse resultResponse) {
        return ResultResponseDto.builder()
                .ticketId(resultResponse.ticketId())
                .numbers(resultResponse.numbers())
                .hitNumbers(resultResponse.hitNumbers())
                .drawDate(resultResponse.drawDate())
                .isWinner(resultResponse.isWinner())
                .build();
    }

    public static ResultResponse mapToResultResponse(ResultDto resultDto) {
        return ResultResponse.builder()
                .ticketId(resultDto.ticketId())
                .numbers(resultDto.numbers())
                .hitNumbers(resultDto.hitNumbers())
                .drawDate(resultDto.drawDate())
                .isWinner(resultDto.isWinner())
                .build();
    }
    public static ResultResponseDto mapFromResultDtoToResultResponseDto(ResultDto resultDto) {
        return ResultResponseDto.
                builder()
                .ticketId(resultDto.ticketId())
                .numbers(resultDto.numbers())
                .hitNumbers(resultDto.hitNumbers())
                .drawDate(resultDto.drawDate())
                .isWinner(resultDto.isWinner())
                .build();
    }
}
