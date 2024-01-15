package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightBuilder {
    //Создаем список полетов
    static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //normal flight with two-hour duration
                createFlights(
                        "Moscow","Paris",
                        threeDaysFromNow,
                        threeDaysFromNow.plusHours(2)),
                //normal multi-segment flight
                createFlights(
                        "Paris","London",
                        threeDaysFromNow,
                        threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3),
                        threeDaysFromNow.plusHours(5)),
                //flight departing in the past
                createFlights(
                        "Paris","Berlin",
                        threeDaysFromNow.minusDays(6),
                        threeDaysFromNow),
                // flight that departs before it arrives
                createFlights(
                        "Paris","Berlin",
                        threeDaysFromNow,
                        threeDaysFromNow.minusHours(6)),
                //flight with more than two hours or ground time
                createFlights(
                        "Berlin","Paris",
                        threeDaysFromNow,
                        threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5),
                        threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours of ground time
                createFlights(
                        "Berlin","Paris",
                        threeDaysFromNow,
                        threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3),
                        threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6),
                        threeDaysFromNow.plusHours(7)));

    }
    //Метод создания полета. Если нечетное количество дат, то исключение
    private static Flight createFlights(final String departureCity,
                                        final String arrivalCity,
                                        final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException("you must pass an even number of dates");
        }
        //Список сегментов, который будет использоваться для создания полета
        //Размер списка равен половине количества переданных дат
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        //цикл по каждой паре дат и создает сегмент для каждой пары
        for (int i = 0; i < (dates.length - 1); i += 2) {
            //добавление сегмента в список
            segments.add(new Segment(departureCity, arrivalCity, dates[i], dates[i + 1]));
        }
        //возврат нового обьекта , используя список сегментов
        return new Flight(segments);
    }
}
