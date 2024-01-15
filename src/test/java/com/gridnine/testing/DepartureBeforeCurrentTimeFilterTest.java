package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DepartureBeforeCurrentTimeFilterTest {

    private DepartureBeforeCurrentTimeFilter departureBeforeCurrentTimeFilter;

    @BeforeEach
    void setUp() {
        departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilter();
    }
    @Test
    void testFilterWithValidFlights(){

        List<Flight> flights = createValidFlights();
        List<Flight> filteredFlights = departureBeforeCurrentTimeFilter.filter(flights);

        System.out.println("Original flights: "+flights);
        System.out.println("Filtered flights: "+filteredFlights);
        assertTrue(filteredFlights.isEmpty(),"Filter must not remove valid flight");

    }
    private boolean areFlightsEqual(Flight expected, Flight actual) {
        if (expected==null || actual==null){
            return false;
        }
        if (expected.getSegments().size() != actual.getSegments().size()){
            return false;
        }
        for (int j = 0; j < expected.getSegments().size(); j++) {
            Segment expectedSegment = expected.getSegments().get(j);
            Segment actualSegment = actual.getSegments().get(j);

            if (!expectedSegment.getDepartureDate().equals(actualSegment.getDepartureDate())) {
                System.out.println("Departure date do not match"+expectedSegment.getDepartureDate()+",Actual - "+actualSegment.getDepartureDate());
                return false;
            }
            if (!expectedSegment.getArrivalDate().equals(actualSegment.getArrivalDate())) {
                System.out.println("Arrival date do not match"+expectedSegment.getArrivalDate()+",Actual - "+actualSegment.getArrivalDate());
                return false;
            }
        }

           return true;
        }

    @Test
    void testFilterWithInvalidFlight(){

        List<Flight> flights = createInvalidFlights();
        List<Flight> filteredFlights = departureBeforeCurrentTimeFilter.filter(flights);
        assertEquals(flights.size(),filteredFlights.size(),"Filter must not remove valid flight");
    }
    @Test
    void testFilterWithNullFlight(){

        List<Flight> filteredFlights = departureBeforeCurrentTimeFilter.filter(null);
        assertNull(filteredFlights,"Filter must return null for null input");
    }

    private List<Flight> createInvalidFlights() {
        List<Flight> flights = new ArrayList<>();
        Flight invalidFlight1 = createFlight("Paris","Berlin",LocalDateTime.now().minusHours(1),LocalDateTime.now());
        Flight invalidFlight2 = createFlight("Berlin","London",LocalDateTime.now().minusHours(2),LocalDateTime.now().minusHours(1));
        flights.add(invalidFlight1);
        flights.add(invalidFlight2);
        return flights;
    }

    private List<Flight> createValidFlights() {
        List<Flight> flights = new ArrayList<>();
        Flight validFlight1 = createFlight("Paris","Berlin",LocalDateTime.now().plusHours(1),LocalDateTime.now().plusHours(2));
        Flight validFlight2 = createFlight("Berlin","London",LocalDateTime.now().plusHours(2),LocalDateTime.now().plusHours(3));
        flights.add(validFlight1);
        flights.add(validFlight2);
        return flights;
    }

    private Flight createFlight(String departureCity, String arrivalCity,LocalDateTime departure,LocalDateTime arrival){
        Segment segment = new Segment(departureCity, arrivalCity, departure, arrival);
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
          return new Flight(segments);
    }

}