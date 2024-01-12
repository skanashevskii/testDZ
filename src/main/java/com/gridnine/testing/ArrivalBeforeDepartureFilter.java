package com.gridnine.testing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrivalBeforeDepartureFilter implements FlightFilter{

    public List<Flight> filter(List<Flight> flights){
        if (flights !=null) {
            flights.removeIf(flight -> flight.getSegments().stream().anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));
        }
        return flights;
    }
}
