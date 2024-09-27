package org.darekvu.lottoproject.resultchecker;

import lombok.RequiredArgsConstructor;
import org.darekvu.lottoproject.numbergenerator.WinningNumbersGeneratorFacade;
import org.darekvu.lottoproject.numbergenerator.dto.WinningNumbersDto;
import org.darekvu.lottoproject.numberreceiver.NumberReceiverFacade;
import org.darekvu.lottoproject.numberreceiver.dto.TicketDto;
import org.darekvu.lottoproject.resultchecker.dto.PlayersDto;
import org.darekvu.lottoproject.resultchecker.dto.ResultDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersGeneratorFacade winningNumbersGeneratorFacade;
    private final WinnerRetriever winnerRetriever;
    private final PlayerRepository playerRepository;


    public PlayersDto generateWinners() {
        LocalDateTime drawDate = numberReceiverFacade.generateClosestDrawDate();
        List<TicketDto> allTicketsByDate = numberReceiverFacade.retrieveTicketsByDrawDate(drawDate);
        Set<Ticket> tickets = ResultCheckerMapper.mapTicketDtoToTicket(allTicketsByDate);
        WinningNumbersDto winningNumbersDto = winningNumbersGeneratorFacade.generateWinningNumbers();
        Set<Integer> winningNumbers = winningNumbersDto.winningNumbers();
        List<Player> players = winnerRetriever.retrievePlayers(tickets, winningNumbers);
        if (winningNumbers == null || winningNumbers.isEmpty()) {
            return PlayersDto.builder()
                    .message("failed to retrieve players")
                    .build();
        }
        playerRepository.saveAll(players);
        return PlayersDto.builder()
                .results(ResultCheckerMapper.mapPlayerToResults(players))
                .message("successfully retrieved players")
                .build();
    }

    public ResultDto findByTicketId(String ticketId) {
        Player player = playerRepository.findById(ticketId).orElseThrow(() -> new PlayerNotFoundException("player not found exception"));
        return ResultDto.builder()
                .ticketId(ticketId)
                .hitNumbers(player.hitNumbers())
                .drawDate(player.drawDate())
                .isWinner(player.isWinner())
                .build();
    }


}
