package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.converter.page.component.ContactPhoneDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.component.ContactPhoneDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactPhoneRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactPhoneRequest;
import com.gungorefe.simpleportfolio.entity.page.Contact;
import com.gungorefe.simpleportfolio.entity.page.component.ContactPhone;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.ContactRepository;
import com.gungorefe.simpleportfolio.repository.page.component.ContactPhoneRepository;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class ContactPhoneService {
    private final ContactRepository contactRepository;
    private final ContactPhoneRepository repository;
    private final ContactPhoneDtoConverter dtoConverter;

    public ContactPhone create(
            String localeName,
            CreateContactPhoneRequest request
    ) {
        Contact contact = contactRepository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.CONTACT));
        ContactPhone contactPhone = dtoConverter.convertToContactPhone(
                request,
                contact
        );

        return repository.save(contactPhone);
    }

    public ContactPhoneDto getDto(int id) {
        ContactPhone contactPhone = repository.findById(id).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.CONTACT_PHONE,
                id
        ));

        return dtoConverter.convertToContactPhoneDto(contactPhone);
    }

    public Set<ContactPhoneDto> getAllDtos(String localeName) {
        LocaleName.validate(localeName);

        return dtoConverter.convertToContactPhoneDtoSet(repository.findAllByLocale_Name(localeName));
    }

    public ContactPhone update(
            UpdateContactPhoneRequest request,
            String localeName
    ) {
        ContactPhone contactPhone = repository.findIdAndContactAndLocaleByIdAndLocale_Name(
                request.id(),
                localeName
        ).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.CONTACT_PHONE,
                request.id()
        ));
        ContactPhone newContactPhone = dtoConverter.convertToContactPhone(
                contactPhone,
                request
        );

        return repository.save(newContactPhone);
    }

    public void delete(
            int id,
            String localeName
    ) {
        boolean exists = repository.existsByIdAndLocale_Name(
                id,
                localeName
        );

        if (!exists) {
            throw ExceptionFactory.getComponentNotFoundException(
                    ComponentName.CONTACT_PHONE,
                    id
            );
        }

        repository.deleteById(id);
    }
}
