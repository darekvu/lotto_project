package org.darekvu.lottoproject.resultchecker;

import org.darekvu.lottoproject.numbergenerator.WinningNumbersGeneratorFacade;
import org.darekvu.lottoproject.numberreceiver.NumberReceiverFacade;

public class ResultCheckerFacadeConfiguration {
    public ResultCheckerFacade createForTest(NumberReceiverFacade numberReceiverFacade, WinningNumbersGeneratorFacade winningNumbersGeneratorFacade, PlayerRepository playerRepository) {
        WinnerRetriever winnerRetriever = new WinnerRetriever();
        return new ResultCheckerFacade(numberReceiverFacade, winningNumbersGeneratorFacade, winnerRetriever, playerRepository);
    }
}
