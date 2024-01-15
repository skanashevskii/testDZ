package com.gridnine.testing;


import java.util.List;

public class ArrivalBeforeDepartureFilter implements Filter {

    public List<Flight> filter(List<Flight> flights){
        if (flights !=null) {
            //удаляет сегмент с датой прибытия раньше отправления
            flights.forEach(flight -> flight.getSegments().removeIf(segment ->
                    segment.getArrivalDate().isBefore(segment.getDepartureDate())));
        }
        return flights;
    }
}
