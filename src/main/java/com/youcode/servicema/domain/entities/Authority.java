package com.youcode.servicema.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.youcode.servicema.domain.enums.AuthorityEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"roles"}, allowSetters = true)
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToMany(mappedBy = "authorities")
    List<Role> roles;

    @Enumerated(EnumType.STRING)
    AuthorityEnum name;
}

