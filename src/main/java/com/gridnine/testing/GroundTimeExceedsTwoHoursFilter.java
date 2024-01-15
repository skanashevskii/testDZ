package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

public class GroundTimeExceedsTwoHoursFilter implements Filter {

    private Duration minGroundTime;

    public GroundTimeExceedsTwoHoursFilter(Duration minGroundTime) {
        this.minGroundTime = minGroundTime;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        if (flights==null){
            return null;
        }
        //сегмент останется в списке если между сегментами более 2 часов
        return flights.stream()
                .filter(flight -> calculateTotalGroundTime(flight.getSegments()).compareTo(minGroundTime) > 0)
                .collect(Collectors.toList());
    }
    //метод расчета стоянки между сегментами полета
    private Duration calculateTotalGroundTime(List<Segment> segments) {
        //если ничего и сегментов меньше 2,то время 0
        if (segments == null || segments.size() < 2) {
            return Duration.ZERO;
        }
        //расчет времени между сегментами
        Duration totalGroundTime = Duration.ZERO;
        for (int i = 1; i < segments.size(); i++) {
            //получение даты прибытия предыдущего сегмента
            LocalDateTime previousArrival = segments.get(i - 1).getArrivalDate();
            //получение даты отправления текущего сегмента
            LocalDateTime currentDeparture = segments.get(i).getDepartureDate();
            //расчет времени между сегментами
            if (currentDeparture.isAfter(previousArrival)) {
                Duration groundTime = Duration.between(previousArrival, currentDeparture);
                System.out.println("Ground time: " + groundTime.toHours() + " hours " + groundTime.toMinutesPart() + " minutes");
                //подсчет общего времени стоянки
                totalGroundTime = totalGroundTime.plus(groundTime);
            }else {
                System.out.println("Invalid segment order: currentDeparture is before previousArrival");
            }

        }
        return totalGroundTime;
    }
}
