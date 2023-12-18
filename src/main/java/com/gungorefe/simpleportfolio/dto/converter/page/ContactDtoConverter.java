package com.gungorefe.simpleportfolio.dto.converter.page;

import com.gungorefe.simpleportfolio.dto.converter.page.component.ContactSimpleCardDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.ContactDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateContactRequest;
import com.gungorefe.simpleportfolio.entity.page.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactDtoConverter {
    private final ContactSimpleCardDtoConverter contactSimpleCardDtoConverter;

    public ContactDto convertToContactDto(Contact contact) {
        ContactDto dto = new ContactDto(
                contact.getTitle(),
                contact.getText(),
                contact.getEmail(),
                contact.getLocation(),
                contact.getWorkingDays(),
                contact.getWorkingHours(),
                contact.getGoogleMapsCoordination(),
                contactSimpleCardDtoConverter.convertToContactSimpleCardDtoList(contact.getContactSimpleCards())
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
