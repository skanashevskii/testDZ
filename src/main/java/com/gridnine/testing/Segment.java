package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Segment {
    private final String departureCity;
    private final String arrivalCity;
    private final LocalDateTime departureDate;
    private final LocalDateTime arrivalDate;

    public Segment(String departureCity,
                   String arrivalCity,
                   LocalDateTime departureDate,
                   LocalDateTime arrivalDate) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' Time 'HH:mm");
        return "Departure: " + departureCity + "" +
                " at " + departureDate.format(formatter) +
                " | Arrival: " + arrivalCity + " at " + arrivalDate.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(departureCity, segment.departureCity) &&
                Objects.equals(arrivalCity, segment.arrivalCity) &&
                Objects.equals(departureDate, segment.departureDate) &&
                Objects.equals(arrivalDate, segment.arrivalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureCity, arrivalCity, departureDate, arrivalDate);
    }
}
