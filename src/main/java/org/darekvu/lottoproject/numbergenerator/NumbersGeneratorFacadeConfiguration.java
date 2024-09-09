package org.darekvu.lottoproject.numbergenerator;

import org.darekvu.lottoproject.numberreceiver.NumberReceiverFacade;

public class NumbersGeneratorFacadeConfiguration {

    public WinningNumbersGeneratorFacade createForTest(WinningNumbersRepository repository, NumberReceiverFacade numberReceiver, RandomNumberGenerable randomNumberGenerator) {
        WinningNumbersValidator winningNumbersValidator = new WinningNumbersValidator();
        return new WinningNumbersGeneratorFacade(randomNumberGenerator, winningNumbersValidator, repository, numberReceiver);
    }
}
