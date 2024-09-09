package org.darekvu.lottoproject.numberreceiver;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {
    private final Clock clock;
    private static final LocalTime DRAW_TIME = LocalTime.of(12, 0, 0);
    private static final TemporalAdjuster NEXT_DRAW_DATE = TemporalAdjusters.next(DayOfWeek.SATURDAY);

    DrawDateGenerator(Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime generateDrawDate() {
        LocalDateTime currentDateTime = LocalDateTime.now(clock);
        if (isSaturdayBeforeNoon(currentDateTime)) {
            return currentDateTime.with(DRAW_TIME);
        }
        LocalDateTime nextDrawDate = currentDateTime.with(NEXT_DRAW_DATE);
        return LocalDateTime.of(nextDrawDate.toLocalDate(), DRAW_TIME);
    }

    private boolean isSaturdayBeforeNoon(LocalDateTime drawDate) {
        return drawDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && drawDate.toLocalTime().isBefore(DRAW_TIME);
    }
}
