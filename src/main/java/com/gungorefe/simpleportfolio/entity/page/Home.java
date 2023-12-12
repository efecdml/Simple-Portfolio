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
@Table(name = "home_pages")
@Entity
public class Home implements Page {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String title;
    private String text;
    private String secondTitle;
    private String secondText;
    @JoinColumn(name = "locale_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public Home(int id, int localeId) {
        this.id = id;
        this.locale = new Locale(localeId);
    }

    public Home(String title, String text, String secondTitle, String secondText, Locale locale) {
        this.title = title;
        this.text = text;
        this.secondTitle = secondTitle;
        this.secondText = secondText;
        this.locale = locale;
    }
}
