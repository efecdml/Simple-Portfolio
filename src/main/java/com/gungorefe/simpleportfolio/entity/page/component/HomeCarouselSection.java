package com.gungorefe.simpleportfolio.entity.page.component;

import com.gungorefe.simpleportfolio.entity.page.Home;
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
@Table(name = "home_carousel_section_components")
@Entity
public class HomeCarouselSection implements Component {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String imageName;
    private String text;
    private int displayOrder;
    @JoinColumn(name = "home_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Home home;
    @JoinColumn(name = "locale_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public HomeCarouselSection(int id, String imageName) {
        this.id = id;
        this.imageName = imageName;
    }

    public HomeCarouselSection(int id, String imageName, int homeId, int localeId) {
        this.id = id;
        this.imageName = imageName;
        this.home = new Home(homeId);
        this.locale = new Locale(localeId);
    }

    public HomeCarouselSection(String imageName, String text, int displayOrder, Home home, Locale locale) {
        this.imageName = imageName;
        this.text = text;
        this.displayOrder = displayOrder;
        this.home = home;
        this.locale = locale;
    }
}
