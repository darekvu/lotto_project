package org.darekvu.lottoproject.domain.numberannouncer;

import lombok.RequiredArgsConstructor;
import org.darekvu.lottoproject.domain.numberannouncer.dto.ResultAnnouncerResponseDto;
import org.darekvu.lottoproject.domain.resultchecker.ResultCheckerFacade;
import org.darekvu.lottoproject.domain.resultchecker.dto.ResultDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@RequiredArgsConstructor
public class NumberAnnouncerFacade {
    public static final LocalTime RESULTS_ANNOUNCEMENT_TIME = LocalTime.of(12, 0).plusMinutes(5);
    private final ResultCheckerFacade resultCheckerFacade;
    private final ResultResponseRepository resultResponseRepository;
    private final Clock clock;

    public ResultAnnouncerResponseDto checkResult(String ticketId) {
        //check if already exists in the ResultResponse;
        if (resultResponseRepository.exists(ticketId)) {
            Optional<ResultResponse> resultResponseCached = resultResponseRepository.findById(ticketId);
            if (resultResponseCached.isPresent()) {
                return new ResultAnnouncerResponseDto(ResponseMapper.mapToResultResponseDto(resultResponseCached.get()), MessageResponse.ALREADY_CHECKED.message);
            }
        }

        ResultDto resultDto = resultCheckerFacade.findByTicketId(ticketId);
        if (resultDto == null) {
            return new ResultAnnouncerResponseDto(null, MessageResponse.TICKET_ID_DOES_NOT_EXIST_MESSAGE.message);
        }
        ResultResponse resultResponse = ResponseMapper.mapToResultResponse(resultDto);
        resultResponseRepository.save(resultResponse);
        if (!isAfterAnnouncementTime(resultDto)) {
            return new ResultAnnouncerResponseDto(ResponseMapper.mapFromResultDtoToResultResponseDto(resultDto), MessageResponse.WAIT_MESSAGE.message);
        }
        if (resultDto.isWinner()) {
            return new ResultAnnouncerResponseDto(ResponseMapper.mapFromResultDtoToResultResponseDto(resultDto), MessageResponse.WIN_MESSAGE.message);
        }
        return new ResultAnnouncerResponseDto(ResponseMapper.mapFromResultDtoToResultResponseDto(resultDto), MessageResponse.LOSE_MESSAGE.message);
    }

    public boolean isAfterAnnouncementTime(ResultDto resultDto) {
        LocalDateTime announcementDateTime = LocalDateTime.of(resultDto.drawDate().toLocalDate(), RESULTS_ANNOUNCEMENT_TIME);
        return LocalDateTime.now(clock).isAfter(announcementDateTime);
    }
}
