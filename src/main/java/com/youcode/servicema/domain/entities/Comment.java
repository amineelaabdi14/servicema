package com.youcode.servicema.domain.entities;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    Service service;
    @ManyToOne(fetch=FetchType.EAGER)
    User user;
}
