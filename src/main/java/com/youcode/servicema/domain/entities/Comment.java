package com.youcode.servicema.domain.entities;

import jakarta.persistence.*;

import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch=FetchType.LAZY)
    Service service;
    @ManyToOne(fetch=FetchType.EAGER)
    User user;
    String comment;
}
