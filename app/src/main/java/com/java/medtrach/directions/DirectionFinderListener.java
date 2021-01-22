package com.java.medtrach.directions;

import com.directions.route.Route;

import java.util.List;

public interface DirectionFinderListener {
    void onDirectionFinderStart();

    void onDirectionFinderSuccess(List<Route> route);
}