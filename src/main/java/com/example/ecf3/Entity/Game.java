package com.example.ecf3.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User player1;

    @ManyToOne
    private User player2;

    @Temporal(TemporalType.DATE)
    private LocalDate dateMatch;

    @OneToOne(mappedBy = "match")
    private Result result;

}
