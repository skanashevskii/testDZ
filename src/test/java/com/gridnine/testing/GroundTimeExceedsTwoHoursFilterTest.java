package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GroundTimeExceedsTwoHoursFilterTest {

    private GroundTimeExceedsTwoHoursFilter groundTimeExceedsTwoHoursFilter;

    @BeforeEach
    void setUp() {
        groundTimeExceedsTwoHoursFilter = new GroundTimeExceedsTwoHoursFilter(Duration.ofHours(2).plusMinutes(1));

    }
    @Test
    void testFilterWithExceedingGroundTime() {

        List<Flight> flights = createFlightsWithExceedingGroundTime();
        System.out.println("Flight before filtr:");
        flights.forEach(System.out::println);

        List<Flight> filteredFlights = groundTimeExceedsTwoHoursFilter.filter(new ArrayList<>(flights));
        System.out.println("Flight after filtr:");
        filteredFlights.forEach(System.out::println);
        assertEquals(1,filteredFlights.size(), "Filter must remove flights with exceeding ground time");
    }

    @Test
    void testFilterWithSatisfyingFlight(){
        List<Flight> satisfyingFlights = createSatisfyingFlights();
        System.out.println("Satisfying Flight before filtr: "+satisfyingFlights);

        List<Flight> filteredFlights = groundTimeExceedsTwoHoursFilter.filter(satisfyingFlights);
        System.out.println("Satisfying Flight after filtr: "+filteredFlights);


        assertEquals(satisfyingFlights.size(),filteredFlights.size(),"Filter must not remove satisfying flight");
    }
    private Flight createFlightWithSegments(List<Segment> segments) {
        return new Flight(new ArrayList<>(segments));
    }

    private List<Flight> createSatisfyingFlights() {
        String departureCity = "Paris";
        String arrivalCity = "London";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departure1 = now.minusHours(3);
        LocalDateTime arrival1 = now.minusHours(1);
        LocalDateTime departure2 = now.plusMinutes(1);

        LocalDateTime arrival2 = now.plusHours(3);
        Segment segment1 = new Segment(departureCity, arrivalCity, departure1, arrival1);
        Segment segment2 = new Segment(departureCity, arrivalCity, departure2, arrival2);

        List<Segment> segments =List.of(segment1,segment2);


        return List.of(createFlightWithSegments(segments));
    }

    private List<Flight> createFlightsWithExceedingGroundTime() {
        String departureCity = "Paris";
        String arrivalCity = "London";
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime departure1 = now.minusHours(3);
        LocalDateTime arrival1 = now.minusHours(2).minusMinutes(32);
        LocalDateTime departure2 = now.minusMinutes(30);


        Segment segment1 = new Segment(departureCity, arrivalCity, departure1, arrival1);
        Segment segment2 = new Segment(departureCity, arrivalCity, departure2, now);

        List<Segment> segments = List.of(segment1,segment2);

        return List.of(createFlightWithSegments(segments));
    }


}