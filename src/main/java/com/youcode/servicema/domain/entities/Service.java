package com.youcode.servicema.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Category category;
    String title;
    String description;
    Long startingPrice;
    String image;
    @OneToMany(mappedBy = "service")
    List<Report> reports;
    @OneToMany(mappedBy = "service")
    List<Comment> comments;
}
