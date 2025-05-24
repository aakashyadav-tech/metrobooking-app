package com.metroapp.model;

import lombok.Data;

import java.util.Map;

@Data
public class StationMap {
    Map<String,Station> stations;
}
