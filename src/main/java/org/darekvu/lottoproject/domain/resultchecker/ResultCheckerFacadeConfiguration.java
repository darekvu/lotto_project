package org.darekvu.lottoproject.domain.resultchecker;

import org.darekvu.lottoproject.domain.numbergenerator.WinningNumbersGeneratorFacade;
import org.darekvu.lottoproject.domain.numberreceiver.NumberReceiverFacade;

public class ResultCheckerFacadeConfiguration {
    public ResultCheckerFacade createForTest(NumberReceiverFacade numberReceiverFacade, WinningNumbersGeneratorFacade winningNumbersGeneratorFacade, PlayerRepository playerRepository) {
        WinnerRetriever winnerRetriever = new WinnerRetriever();
        return new ResultCheckerFacade(numberReceiverFacade, winningNumbersGeneratorFacade, winnerRetriever, playerRepository);
    }
}
