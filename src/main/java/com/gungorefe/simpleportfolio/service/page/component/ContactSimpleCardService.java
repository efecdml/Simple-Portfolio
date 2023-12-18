package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.converter.page.component.ContactSimpleCardDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.component.ContactSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.Contact;
import com.gungorefe.simpleportfolio.entity.page.component.ContactSimpleCard;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.ContactRepository;
import com.gungorefe.simpleportfolio.repository.page.component.ContactSimpleCardRepository;
import com.gungorefe.simpleportfolio.service.ImageService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class ContactSimpleCardService {
    private final ImageService imageService;
    private final ContactRepository contactRepository;
    private final ContactSimpleCardRepository repository;
    private final ContactSimpleCardDtoConverter dtoConverter;

    public ContactSimpleCard create(
            MultipartFile image,
            String localeName,
            CreateContactSimpleCardRequest request
    ) {
        LocaleName.validate(localeName);

        String imageName = imageService.save(
                image,
                null
        );
        Contact contact = contactRepository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.CONTACT));
        ContactSimpleCard contactSimpleCard = dtoConverter.convertToContactSimpleCard(
                imageName,
                request,
                contact
        );

        return repository.save(contactSimpleCard);
    }

    public ContactSimpleCardDto getDto(int id) {
        ContactSimpleCard contactSimpleCard = repository.findById(id).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.CONTACT_SIMPLE_CARD,
                id
        ));

        return dtoConverter.convertToContactSimpleCardDto(contactSimpleCard);
    }

    public Set<ContactSimpleCardDto> getAllDtos(String localeName) {
        LocaleName.validate(localeName);

        return dtoConverter.convertToContactSimpleCardDtoSet(repository.findAllByLocale_Name(localeName));
    }

    public Image getImage(
            int id,
            String imageName
    ) {
        String currentImageName = repository.findImageNameById(id);

        if (!currentImageName.equals(imageName)) {
            throw ExceptionFactory.getImageNotFoundException(imageName);
        }

        return imageService.get(imageName);
    }

    public ContactSimpleCard update(
            @Nullable MultipartFile image,
            UpdateContactSimpleCardRequest request
    ) {
        ContactSimpleCard contactSimpleCard = repository.findIdAndImageNameAndContactAndLocaleById(request.id())
                .orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                        ComponentName.CONTACT_SIMPLE_CARD,
                        request.id()
                ));
        String currentImageName = contactSimpleCard.getImageName();
        String imageName = image == null
                ? currentImageName
                : imageService.save(image, currentImageName);
        ContactSimpleCard newContactSimpleCard = dtoConverter.convertToContactSimpleCard(
                contactSimpleCard,
                imageName,
                request
        );

        return repository.save(newContactSimpleCard);
    }

    public void delete(int id) {
        ContactSimpleCard contactSimpleCard = repository.findIdAndImageNameById(id).orElseThrow(() -> ExceptionFactory
                .getComponentNotFoundException(
                        ComponentName.CONTACT_SIMPLE_CARD,
                        id
                ));

        repository.delete(contactSimpleCard);
        imageService.delete(contactSimpleCard.getImageName());
    }
}
