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
@Table(name = "works_pages")
@Entity
public class Works implements Page {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String imageName;
    private String title;
    @Column(columnDefinition = "text")
    private String text;
    @JoinColumn(name = "locale_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public Works(int id, String imageName, int localeId) {
        this.id = id;
        this.imageName = imageName;
        this.locale = new Locale(localeId);
    }

    public Works(String imageName, String title, String text, Locale locale) {
        this.imageName = imageName;
        this.title = title;
        this.text = text;
        this.locale = locale;
    }
}
