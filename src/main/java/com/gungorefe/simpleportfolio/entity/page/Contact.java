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
@Table(name = "contact_pages")
@Entity
public class Contact implements Page {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String title;
    @Column(columnDefinition = "text")
    private String text;
    private String email;
    @Column(columnDefinition = "text")
    private String location;
    private String workingDays;
    private String workingHours;
    @Column(columnDefinition = "text")
    private String googleMapsCoordination;
    @JoinColumn(name = "locale_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public Contact(int id, int localeId) {
        this.id = id;
        this.locale = new Locale(localeId);
    }

    public Contact(String title, String text, String email, String location, String workingDays, String workingHours, String googleMapsCoordination, Locale locale) {
        this.title = title;
        this.text = text;
        this.email = email;
        this.location = location;
        this.workingDays = workingDays;
        this.workingHours = workingHours;
        this.googleMapsCoordination = googleMapsCoordination;
        this.locale = locale;
    }
}
