package com.youcode.servicema.domain.entities;

import jakarta.persistence.*;

import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Service service;
    @ManyToOne
    User user;
    String message;
}
