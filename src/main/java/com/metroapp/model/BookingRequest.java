package com.metroapp.model;

import lombok.Data;

@Data
public class BookingRequest {
    private String entryStation;
    private String exitStation;
}
