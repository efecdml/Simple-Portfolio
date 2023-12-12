package com.gungorefe.simpleportfolio.entity.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Table(name = "locales")
@Entity
public class Locale {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String name;
    @OneToOne(mappedBy = "locale")
    private Home home;

    public Locale(String name) {
        this.name = name;
    }

    public Locale(int id) {
        this.id = id;
    }
}
