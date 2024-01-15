package com.gridnine.testing;

import java.time.Duration;
import java.util.List;

public class FlightFilter implements Filter{

    private Duration minGroundTime;

    public FlightFilter(Duration minGroundTime){
        this.minGroundTime=minGroundTime;
    }
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return null;
    }
}
