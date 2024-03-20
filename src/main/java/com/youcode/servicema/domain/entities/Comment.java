package com.youcode.servicema.domain.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    Service service;
    @ManyToOne(fetch=FetchType.EAGER)
    User user;
}
