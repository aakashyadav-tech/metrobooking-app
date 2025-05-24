package com.metroapp.controller;

import com.metroapp.model.BookingRequest;
import com.metroapp.model.BookingResponse;
import com.metroapp.model.UseTicket;
import com.metroapp.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }


    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookTicket(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.status(201).body(ticketService.createTicket(bookingRequest));
    }

    @PostMapping("/use")
    public ResponseEntity<String> enter(@RequestBody UseTicket useTicket) {
        String response = useTicket.getAction().equals("ENTER")
                ? ticketService.enterStation(useTicket.getTicketId())
                : ticketService.exitStation(useTicket.getTicketId());
        return ResponseEntity.status(200).body(response);
    }





}
