package com.gridnine.testing;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        FlightFilter departureFilter = new DepartureBeforeCurrentTimeFilter();
        FlightFilter arrivalFilter = new ArrivalBeforeDepartureFilter();
        FlightFilter groundTimeFilter = new GroundTimeExceedsTwoHoursFilter();

        //All flights
        System.out.println("\nOriginal flights: ");
        printFlights(flights);

        // Filters flights
        System.out.println("\nFlights after departure filter: ");
        printFlights(departureFilter.filter(flights));

        System.out.println("\nFlights after arrival filter: ");
        printFlights(arrivalFilter.filter(flights));
        System.out.println("\nFlights after ground time filter: ");
        printFlights(groundTimeFilter.filter(flights));

    }

    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
        //System.out.println("Flight: " );
        for (Segment segment : flight.getSegments()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' Time 'HH:mm");
            System.out.println("[Departure: "+segment.getDepartureDate().format(formatter)+ " | Arrival: " +segment.getArrivalDate().format(formatter)+"]");
        }
    }
    }
}