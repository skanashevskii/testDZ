package com.gridnine.testing;


import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeCurrentTimeFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        if (flights==null){
            return null;
        }
        return flights.stream()
                    .filter(flight -> {
                        boolean shouldKeep = flight.getSegments().stream()
                                .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()));
                        if (!shouldKeep) {
                            System.out.println("Filtered flight: "+flight);
                        }
                        return shouldKeep;
                    })
                    .collect(Collectors.toList());
    }




}






