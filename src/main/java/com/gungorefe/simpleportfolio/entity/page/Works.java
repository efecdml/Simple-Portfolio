package com.gungorefe.simpleportfolio.entity.page;

import com.gungorefe.simpleportfolio.entity.page.component.WorksDetailedCard;
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
    @OneToMany(mappedBy = "works")
    private Collection<WorksDetailedCard> worksDetailedCards;
    @JoinColumn(name = "locale_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public Works(int id) {
        this.id = id;
    }

    public Works(int id, int localeId) {
        this.id = id;
        this.locale = new Locale(localeId);
    }

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
