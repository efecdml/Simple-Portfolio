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
@Table(name = "home_simple_card_components")
@Entity
public class HomeSimpleCard implements Component {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String imageName;
    private String title;
    private String text;
    private int displayOrder;
    @JoinColumn(name = "home_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Home home;
    @JoinColumn(name = "locale_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public HomeSimpleCard(int id, String imageName) {
        this.id = id;
        this.imageName = imageName;
    }

    public HomeSimpleCard(int id, String imageName, int homeId, int localeId) {
        this.id = id;
        this.imageName = imageName;
        this.home = new Home(homeId);
        this.locale = new Locale(localeId);
    }

    public HomeSimpleCard(String imageName, String title, String text, int displayOrder, Home home, Locale locale) {
        this.imageName = imageName;
        this.title = title;
        this.text = text;
        this.displayOrder = displayOrder;
        this.home = home;
        this.locale = locale;
    }
}
