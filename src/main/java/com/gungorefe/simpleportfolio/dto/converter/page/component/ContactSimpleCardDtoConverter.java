package com.gungorefe.simpleportfolio.dto.converter.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.ContactSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.Contact;
import com.gungorefe.simpleportfolio.entity.page.component.ContactSimpleCard;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContactSimpleCardDtoConverter {
    public ContactSimpleCardDto convertToContactSimpleCardDto(ContactSimpleCard contactSimpleCard) {
        ContactSimpleCardDto dto = new ContactSimpleCardDto(
                contactSimpleCard.getId(),
                contactSimpleCard.getImageName(),
                contactSimpleCard.getTitle(),
                contactSimpleCard.getText(),
                contactSimpleCard.getDisplayOrder()
        );

        return dto;
    }

    public List<ContactSimpleCardDto> convertToContactSimpleCardDtoList(Collection<ContactSimpleCard> contactSimpleCards) {
        List<ContactSimpleCardDto> dtos = contactSimpleCards.stream()
                .map(this::convertToContactSimpleCardDto)
                .toList();

        return dtos;
    }

    public Set<ContactSimpleCardDto> convertToContactSimpleCardDtoSet(Collection<ContactSimpleCard> contactSimpleCards) {
        Set<ContactSimpleCardDto> dtos = contactSimpleCards.stream()
                .map(this::convertToContactSimpleCardDto)
                .collect(Collectors.toSet());

        return dtos;
    }

    public ContactSimpleCard convertToContactSimpleCard(
            String imageName,
            CreateContactSimpleCardRequest request,
            Contact contact
    ) {
        ContactSimpleCard contactSimpleCard = new ContactSimpleCard(
                imageName,
                request.title(),
                request.text(),
                request.displayOrder(),
                contact,
                contact.getLocale()
        );

        return contactSimpleCard;
    }

    public ContactSimpleCard convertToContactSimpleCard(
            ContactSimpleCard contactSimpleCard,
            String imageName,
            UpdateContactSimpleCardRequest request
    ) {
        contactSimpleCard.setImageName(imageName);
        contactSimpleCard.setTitle(request.title());
        contactSimpleCard.setText(request.text());
        contactSimpleCard.setDisplayOrder(request.displayOrder());

        return contactSimpleCard;
    }
}
