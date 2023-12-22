package com.gungorefe.simpleportfolio.dto.converter.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.ContactPhoneDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactPhoneRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactPhoneRequest;
import com.gungorefe.simpleportfolio.entity.page.Contact;
import com.gungorefe.simpleportfolio.entity.page.component.ContactPhone;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ContactPhoneDtoConverter {
    public ContactPhoneDto convertToContactPhoneDto(ContactPhone contactPhone) {
        ContactPhoneDto dto = new ContactPhoneDto(
                contactPhone.getId(),
                contactPhone.getTag(),
                contactPhone.getNumber(),
                contactPhone.getDisplayOrder()
        );

        return dto;
    }

    public List<ContactPhoneDto> convertToContactPhoneDtoList(Collection<ContactPhone> contactPhones) {
        List<ContactPhoneDto> dtos = contactPhones.stream()
                .map(this::convertToContactPhoneDto)
                .toList();

        return dtos;
    }

    public Set<ContactPhoneDto> convertToContactPhoneDtoSet(Collection<ContactPhone> contactPhones) {
        Set<ContactPhoneDto> dtos = contactPhones.stream()
                .map(this::convertToContactPhoneDto)
                .collect(Collectors.toSet());

        return dtos;
    }

    public ContactPhone convertToContactPhone(
            CreateContactPhoneRequest request,
            Contact contact
    ) {
        ContactPhone contactPhone = new ContactPhone(
                request.tag(),
                request.number(),
                request.displayOrder(),
                contact,
                contact.getLocale()
        );

        return contactPhone;
    }

    public ContactPhone convertToContactPhone(
            ContactPhone contactPhone,
            UpdateContactPhoneRequest request
    ) {
        contactPhone.setTag(request.tag());
        contactPhone.setNumber(request.number());
        contactPhone.setDisplayOrder(request.displayOrder());

        return contactPhone;
    }
}
