package com.gridnine.testing;


import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeCurrentTimeFilter implements Filter {

    @Override
    public List<Flight> filter(List<Flight> flights) {
        if (flights==null){
            return null;
        }
        //с помощью Stream API обрабатываем список полетов
        return flights.stream()
                //Фильтрация полетов. Для каждого полета проверка через лямбду
                    .filter(flight -> {
                        //хотя бы у одного сегмента дата отправления до текущего времени
                        boolean shouldKeep = flight.getSegments().stream()
                                .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()));
                        //если условие ложь вывод инфо что полет отфильтрован
                        if (!shouldKeep) {
                            System.out.println("Filtered flight: "+flight);
                        }
                        //если условие истина, полет остается в списке
                        return shouldKeep;
                    })
                //сбор результата в список и возврат
                    .collect(Collectors.toList());
    }




}






