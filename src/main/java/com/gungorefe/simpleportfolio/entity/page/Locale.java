package com.gungorefe.simpleportfolio.entity.page;

import com.gungorefe.simpleportfolio.entity.page.component.HomeCarouselSection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

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
    @OneToMany(mappedBy = "locale")
    private Collection<HomeCarouselSection> homeCarouselSections;

    public Locale(String name) {
        this.name = name;
    }

    public Locale(int id) {
        this.id = id;
    }
}
