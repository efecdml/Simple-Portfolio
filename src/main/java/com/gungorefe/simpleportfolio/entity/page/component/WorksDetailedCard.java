package com.gungorefe.simpleportfolio.entity.page.component;

import com.gungorefe.simpleportfolio.entity.page.Locale;
import com.gungorefe.simpleportfolio.entity.page.Works;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@Table(name = "works_detailed_card_components")
@Entity
public class WorksDetailedCard implements Component {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String imageName;
    private String title;
    @Column(columnDefinition = "text")
    private String text;
    private int displayOrder;
    @JoinColumn(name = "works_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Works works;
    @JoinColumn(name = "locale_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public WorksDetailedCard(int id, String imageName) {
        this.id = id;
        this.imageName = imageName;
    }

    public WorksDetailedCard(int id, String imageName, int worksId, int localeId) {
        this.id = id;
        this.imageName = imageName;
        this.works = new Works(worksId);
        this.locale = new Locale(localeId);
    }

    public WorksDetailedCard(String imageName, String title, String text, int displayOrder, Works works, Locale locale) {
        this.imageName = imageName;
        this.title = title;
        this.text = text;
        this.displayOrder = displayOrder;
        this.works = works;
        this.locale = locale;
    }
}
