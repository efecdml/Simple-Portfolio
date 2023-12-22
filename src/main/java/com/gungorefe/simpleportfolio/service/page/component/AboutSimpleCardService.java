package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.converter.page.component.AboutSimpleCardDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.component.AboutSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.About;
import com.gungorefe.simpleportfolio.entity.page.component.AboutSimpleCard;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.AboutRepository;
import com.gungorefe.simpleportfolio.repository.page.component.AboutSimpleCardRepository;
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
public class AboutSimpleCardService {
    private final ImageService imageService;
    private final AboutRepository aboutRepository;
    private final AboutSimpleCardRepository repository;
    private final AboutSimpleCardDtoConverter dtoConverter;

    public AboutSimpleCard create(
            MultipartFile image,
            String localeName,
            CreateAboutSimpleCardRequest request
    ) {
        String imageName = imageService.save(
                image,
                null
        );
        About about = aboutRepository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.ABOUT));
        AboutSimpleCard aboutSimpleCard = dtoConverter.convertToAboutSimpleCard(
                imageName,
                request,
                about
        );

        return repository.save(aboutSimpleCard);
    }

    public AboutSimpleCardDto getDto(int id) {
        AboutSimpleCard aboutSimpleCard = repository.findById(id).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.ABOUT_SIMPLE_CARD,
                id
        ));

        return dtoConverter.convertToAboutSimpleCardDto(aboutSimpleCard);
    }

    public Set<AboutSimpleCardDto> getAllDtos(String localeName) {
        LocaleName.validate(localeName);

        return dtoConverter.convertToAboutSimpleCardDtoSet(repository.findAllByLocale_Name(localeName));
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

    public AboutSimpleCard update(
            @Nullable MultipartFile image,
            UpdateAboutSimpleCardRequest request,
            String localeName
    ) {
        AboutSimpleCard aboutSimpleCard = repository.findIdAndImageNameAndAboutAndLocaleByIdAndLocale_Name(
                request.id(),
                localeName
        ).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.ABOUT_SIMPLE_CARD,
                request.id()
        ));
        String currentImageName = aboutSimpleCard.getImageName();
        String imageName = image == null
                ? currentImageName
                : imageService.save(image, currentImageName);
        AboutSimpleCard newAboutSimpleCard = dtoConverter.convertToAboutSimpleCard(
                aboutSimpleCard,
                imageName,
                request
        );

        return repository.save(newAboutSimpleCard);
    }

    public void delete(
            int id,
            String localeName
    ) {
        AboutSimpleCard aboutSimpleCard = repository.findIdAndImageNameByIdAndLocale_Name(
                id,
                localeName
        ).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.ABOUT_SIMPLE_CARD,
                id
        ));

        repository.delete(aboutSimpleCard);
        imageService.delete(aboutSimpleCard.getImageName());
    }
}
