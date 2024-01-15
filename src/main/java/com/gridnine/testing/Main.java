package com.gridnine.testing;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //создаем список полетов из класса FlightBuilder
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        //Создаем объект с помощью фильтра отборов полета по времени отправления
        Filter departureFilter = new DepartureBeforeCurrentTimeFilter();
        //Создаем объект с помощью фильтра отборов полета по времени прибытия
        Filter arrivalFilter = new ArrivalBeforeDepartureFilter();
        //Создаем объект с помощью фильтра отборов полета по времени стоянки между полетами
        Filter groundTimeFilter = new GroundTimeExceedsTwoHoursFilter(Duration.ofHours(2).plusMinutes(1));

        //All flights
        System.out.println("\nOriginal flights: ");
        printFlights(flights);

        // Печать полетов по отправлению
        System.out.println("\nFlights after departure filter: ");
        printFlights(departureFilter.filter(flights));
        // Печать полетов по прибытию
        System.out.println("\nFlights after arrival filter: ");
        printFlights(arrivalFilter.filter(flights));
        // Печать полетов по времени стоянки более 2 часов
        System.out.println("\nFlights after ground time filter: ");
        printFlights(groundTimeFilter.filter(flights));

    }
    //Метод принимает список полетов и выводит о них инфо
    private static void printFlights(List<Flight> flights) {
        //цикл по всем элементам списка полетов
        for (Flight flight : flights) {
        //Цикл по всем сегментам полета
        for (Segment segment : flight.getSegments()){
            //создаем формат читаемы для вывода
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' Time 'HH:mm");
            //Вывод инфо о каждом сегменте с датой и временем отправления и прибытия
            System.out.println("[ "+segment.getDepartureCity()+
                               " - " +segment.getArrivalCity()+
                                " -> Departure: "+segment.getDepartureDate().format(formatter)+
                                " | Arrival: "+segment.getArrivalDate().format(formatter)+"]");
        }
    }
    }
}