package com.gridnine.testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrivalBeforeDepartureFilterTest {

    private ArrivalBeforeDepartureFilter arrivalBeforeDepartureFilter;

    @BeforeEach
    void setUp() {
        arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilter();
    }
    @Test
    void testFilterWithValidFlights() {

        List<Flight> flights = createValidFlights();

        List<Flight> filteredFlights = arrivalBeforeDepartureFilter.filter(flights);
        assertEquals(flights.size(),filteredFlights.size());
    }
    @Test
    void testFilterWithNullFlights(){

        List<Flight> filteredFlights = arrivalBeforeDepartureFilter.filter(null);

        assertNull(filteredFlights);
    }
    @Test
    void testFilterWithInvalidFlights(){

        List<Flight> flights = createInvalidFlights();
        List<Flight> originalFlights = new ArrayList<>(flights);
        arrivalBeforeDepartureFilter.filter(flights);

        assertArrayEquals(originalFlights.toArray(),flights.toArray(),"Filter must remove invalid flights");

    }

    private List<Flight> createValidFlights() {
        List<Flight> flights = new ArrayList<>();
        Flight flight1 = createFlight(LocalDateTime.of(2024, 1, 13, 10, 0), LocalDateTime.of(2024, 1, 13, 12, 0));
        Flight flight2 = createFlight(LocalDateTime.of(2024, 1, 13, 13, 0), LocalDateTime.of(2024, 1, 13, 15, 0));

        flights.add(flight1);
        flights.add(flight2);

        return flights;
    }

    private List<Flight> createInvalidFlights() {
        Flight invalidFlight = createFlight(LocalDateTime.of(2024,1,13,12,0),
                LocalDateTime.of(2024,1,13,10,0));
        List<Flight> flights = new ArrayList<>();
        flights.add(invalidFlight);

        return flights;
    }



    private static Flight createFlight(LocalDateTime departure, LocalDateTime arrival) {
        Segment segment = new Segment(departure, arrival);
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        return new Flight(segments);
    }

}