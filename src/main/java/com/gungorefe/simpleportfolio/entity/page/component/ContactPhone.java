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
@Table(name = "contact_phone_components")
@Entity
public class ContactPhone implements Component {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private int id;
    private String tag;
    private String number;
    private int displayOrder;
    @JoinColumn(name = "contact_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contact contact;
    @JoinColumn(name = "locale_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Locale locale;

    public ContactPhone(int id, int contactId, int localeId) {
        this.id = id;
        this.contact = new Contact(contactId);
        this.locale = new Locale(localeId);
    }

    public ContactPhone(String tag, String number, int displayOrder, Contact contact, Locale locale) {
        this.tag = tag;
        this.number = number;
        this.displayOrder = displayOrder;
        this.contact = contact;
        this.locale = locale;
    }
}
