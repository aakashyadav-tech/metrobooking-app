package com.metroapp.service;

import com.metroapp.entity.Ticket;
import com.metroapp.exception.InvalidTicketException;
import com.metroapp.model.BookingRequest;
import com.metroapp.model.BookingResponse;
import com.metroapp.model.Station;
import com.metroapp.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final Map<String, Station> stationMap;

    public TicketService(TicketRepository ticketRepository, StationService stationService) {
        this.ticketRepository = ticketRepository;
        this.stationMap = stationService.getStationMap();
    }

    public BookingResponse createTicket(BookingRequest bookingRequest) {
        Ticket ticket = Ticket.builder()
                .id(UUID.randomUUID().toString())
                .entryStation(bookingRequest.getEntryStation())
                .exitStation(bookingRequest.getExitStation())
                .price(calculateFare(bookingRequest.getEntryStation(), bookingRequest.getExitStation()))
                .build();
        Ticket saved = ticketRepository.save(ticket);
        return BookingResponse.builder()
                .ticketId(saved.getId())
                .bookedAt(saved.getCreatedAt())
                .entryStation(saved.getEntryStation())
                .exitStation(saved.getExitStation())
                .price(saved.getPrice())
                .validTill(saved.getCreatedAt().plusHours(18))
                .build();
    }

    public String enterStation(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new InvalidTicketException("No Ticket found for given ticketId"));
        validateTicket(ticket, true);
        ticket.setEntered(true);
        ticketRepository.save(ticket);
        return "Entered successfully.";
    }

    public String exitStation(String ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new InvalidTicketException("No Ticket found for given ticketId"));
        validateTicket(ticket, false);
        ticket.setExited(true);
        ticketRepository.save(ticket);
        return "Exited successfully.";
    }

    private double calculateFare(String entryStation, String exitStation) {

        return Math.abs(stationMap.get(entryStation).getPrice() - stationMap.get(exitStation).getPrice());
    }

    private void validateTicket(Ticket ticket, boolean isEntry) {
        System.out.println("Created At: " + ticket.getCreatedAt());

        if (ticket.getCreatedAt().plusHours(18).isBefore(LocalDateTime.now())) {
            throw new InvalidTicketException("Ticket expired.");
        }
        if (isEntry && ticket.isEntered()) {
            throw new InvalidTicketException("Already entered.");
        }
        if (!isEntry && !ticket.isEntered()) {
            throw new InvalidTicketException("Entry not used yet.");
        }
        if (!isEntry && ticket.isExited()) {
            throw new InvalidTicketException("Already exited.");
        }
    }

}
