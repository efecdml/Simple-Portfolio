package com.gungorefe.simpleportfolio.entity.page.component;

import com.gungorefe.simpleportfolio.entity.page.About;
import com.gungorefe.simpleportfolio.entity.page.Locale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Table(name = "about_simple_card_components")
@Entity
public class AboutSimpleCard implements Component {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String imageName;
    private String title;
    private String text;
    private int displayOrder;
    @JoinColumn(name = "about_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private About about;
    @JoinColumn(name = "locale_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public AboutSimpleCard(int id, String imageName) {
        this.id = id;
        this.imageName = imageName;
    }

    public AboutSimpleCard(int id, String imageName, int aboutId, int localeId) {
        this.id = id;
        this.imageName = imageName;
        this.about = new About(aboutId);
        this.locale = new Locale(localeId);
    }

    public AboutSimpleCard(String imageName, String title, String text, int displayOrder, About about, Locale locale) {
        this.imageName = imageName;
        this.title = title;
        this.text = text;
        this.displayOrder = displayOrder;
        this.about = about;
        this.locale = locale;
    }
}
