package com.gungorefe.simpleportfolio.entity.page.component;

import com.gungorefe.simpleportfolio.entity.page.Contact;
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
@Table(name = "contact_simple_card_components")
@Entity
public class ContactSimpleCard implements Component {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String imageName;
    private String title;
    private String text;
    private int displayOrder;
    @JoinColumn(name = "contact_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contact contact;
    @JoinColumn(name = "locale_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public ContactSimpleCard(int id, String imageName) {
        this.id = id;
        this.imageName = imageName;
    }

    public ContactSimpleCard(int id, String imageName, int contactId, int localeId) {
        this.id = id;
        this.imageName = imageName;
        this.contact = new Contact(contactId);
        this.locale = new Locale(localeId);
    }

    public ContactSimpleCard(String imageName, String title, String text, int displayOrder, Contact contact, Locale locale) {
        this.imageName = imageName;
        this.title = title;
        this.text = text;
        this.displayOrder = displayOrder;
        this.contact = contact;
        this.locale = locale;
    }
}
