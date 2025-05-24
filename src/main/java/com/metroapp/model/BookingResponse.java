package com.metroapp.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingResponse {
    private String ticketId;
    private String entryStation;
    private String exitStation;
    private LocalDateTime validTill;
    private LocalDateTime bookedAt;
    private double price;
}
