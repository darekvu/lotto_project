package org.darekvu.lottoproject.domain.numberannouncer;

import org.darekvu.lottoproject.domain.resultchecker.ResultCheckerFacade;

import java.time.Clock;

public class ResultAnnouncerConfiguration {
    public NumberAnnouncerFacade createForTest(ResultCheckerFacade resultCheckerFacade, ResultResponseRepository resultResponseRepository, Clock clock) {
        return new NumberAnnouncerFacade(resultCheckerFacade, resultResponseRepository, clock);
    }
}
