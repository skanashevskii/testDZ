package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightBuilder {
    static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //normal flight with two-hour duration
                createFlights(
                        threeDaysFromNow,
                        threeDaysFromNow.plusHours(2)),
                //normal multi-segment flight
                createFlights(
                        threeDaysFromNow,
                        threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3),
                        threeDaysFromNow.plusHours(5)),
                //flight departing in the past
                createFlights(
                        threeDaysFromNow.minusDays(6),
                        threeDaysFromNow),
                // flight that departs before it arrives
                createFlights(
                        threeDaysFromNow,
                        threeDaysFromNow.minusHours(6)),
                //flight with more than two hours or ground time
                createFlights(
                        threeDaysFromNow,
                        threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5),
                        threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours of ground time
                createFlights(threeDaysFromNow,
                        threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3),
                        threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6),
                        threeDaysFromNow.plusHours(7)));

    }

    private static Flight createFlights(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException("you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}
