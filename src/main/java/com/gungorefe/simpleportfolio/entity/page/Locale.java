package com.gungorefe.simpleportfolio.entity.page;

import com.gungorefe.simpleportfolio.entity.page.component.*;
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
    @OneToMany(mappedBy = "locale")
    private Collection<HomeSimpleCard> homeSimpleCards;
    @OneToOne(mappedBy = "locale")
    private Works works;
    @OneToMany(mappedBy = "locale")
    private Collection<WorksDetailedCard> worksDetailedCards;
    @OneToOne(mappedBy = "locale")
    private About about;
    @OneToMany(mappedBy = "locale")
    private Collection<AboutSimpleCard> aboutSimpleCards;
    @OneToOne(mappedBy = "locale")
    private Contact contact;
    @OneToMany(mappedBy = "locale")
    private Collection<ContactSimpleCard> contactSimpleCards;
    @OneToMany(mappedBy = "locale")
    private Collection<ContactPhone> contactPhones;

    public Locale(String name) {
        this.name = name;
    }

    public Locale(int id) {
        this.id = id;
    }
}
