package com.gungorefe.simpleportfolio.dto.converter.page;

import com.gungorefe.simpleportfolio.dto.page.ContactDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateContactRequest;
import com.gungorefe.simpleportfolio.entity.page.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoConverter {
    public ContactDto convertToContactDto(Contact contact) {
        ContactDto dto = new ContactDto(
                contact.getTitle(),
                contact.getText(),
                contact.getEmail(),
                contact.getLocation(),
                contact.getWorkingDays(),
                contact.getWorkingHours(),
                contact.getGoogleMapsCoordination()
        );

        return dto;
    }

    public Contact convertToContact(
            Contact contact,
            UpdateContactRequest request
    ) {
        contact.setTitle(request.title());
        contact.setText(request.text());
        contact.setEmail(request.email());
        contact.setLocation(request.location());
        contact.setWorkingDays(request.workingDays());
        contact.setWorkingHours(request.workingHours());
        contact.setGoogleMapsCoordination(request.googleMapsCoordination());

        return contact;
    }
}
