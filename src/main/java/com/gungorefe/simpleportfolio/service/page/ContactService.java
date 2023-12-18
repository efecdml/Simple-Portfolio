package com.gungorefe.simpleportfolio.service.page;

import com.gungorefe.simpleportfolio.dto.converter.page.ContactDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.ContactDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateContactRequest;
import com.gungorefe.simpleportfolio.entity.page.Contact;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.ContactRepository;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContactService {
    private final ContactRepository repository;
    private final ContactDtoConverter dtoConverter;

    public ContactDto getDto(String localeName) {
        Contact contact = repository.findByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.CONTACT));

        return dtoConverter.convertToContactDto(contact);
    }

    public Contact update(
            String localeName,
            UpdateContactRequest request
    ) {
        Contact contact = repository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.CONTACT));
        Contact newContact = dtoConverter.convertToContact(
                contact,
                request
        );

        return repository.save(newContact);
    }
}
