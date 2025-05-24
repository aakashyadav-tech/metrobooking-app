package com.metroapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "stationName")
public class Station {
    private String stationName;
    private double price;
    private boolean startStation;
    private boolean lastStation;

}
