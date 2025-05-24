package com.metroapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ticket {
    @Id
    private String id;
    @Column(nullable = false)
    private String entryStation;
    @Column(nullable = false)
    private String exitStation;
    @Column(nullable = false)
    private double price;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private boolean isEntered;
    private boolean isExited;
}
