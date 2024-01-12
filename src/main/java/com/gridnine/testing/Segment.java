package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Segment {
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;

    public Segment(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureDate(){
        return departureDate;
    }
    public LocalDateTime getArrivalDate(){
        return departureDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' Time 'HH:mm");
        return "Departure: "+departureDate.format(formatter)+ " | Arrival: " +arrivalDate.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(departureDate, segment.departureDate) && Objects.equals(arrivalDate, segment.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureDate, arrivalDate);
    }
}
