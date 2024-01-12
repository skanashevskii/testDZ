package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        if (flights==null){
            return null;
        }
        return flights.stream()
                .filter(flight -> calculateTotalGroundTime(flight.getSegments()) > 2)
                .collect(Collectors.toList());
    }

    private long calculateTotalGroundTime(List<Segment> segments) {
        if (segments == null || segments.size() < 2) {
            return 0;
        }
        long totalGroundTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime previousArrival = segments.get(i - 1).getArrivalDate();
            LocalDateTime currentDeparture = segments.get(i).getDepartureDate();
            long groundTime = Duration.between(previousArrival, currentDeparture).toHours();
            System.out.println("Ground time: "+groundTime);
            totalGroundTime += groundTime;
            /*if (groundTime > 2) {
                totalGroundTime += groundTime;
            }*/
        }
        return totalGroundTime;
    }
}
