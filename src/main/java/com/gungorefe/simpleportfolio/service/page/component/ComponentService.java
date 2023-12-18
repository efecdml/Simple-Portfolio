package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.*;
import com.gungorefe.simpleportfolio.entity.page.component.Component;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class ComponentService {
    private final HomeCarouselSectionService homeCarouselSectionService;
    private final HomeSimpleCardService homeSimpleCardService;
    private final WorksDetailedCardService worksDetailedCardService;
    private final AboutSimpleCardService aboutSimpleCardService;
    private final ContactSimpleCardService contactSimpleCardService;

    public Component create(
            ComponentName componentName,
            @Nullable MultipartFile image,
            CreateComponentRequest request,
            @Nullable String localeName
    ) {
        return switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.create(
                    image,
                    localeName,
                    (CreateHomeCarouselSectionRequest) request
            );
            case HOME_SIMPLE_CARD -> homeSimpleCardService.create(
                    image,
                    localeName,
                    (CreateHomeSimpleCardRequest) request
            );
            case WORKS_DETAILED_CARD -> worksDetailedCardService.create(
                    image,
                    localeName,
                    (CreateWorksDetailedCardRequest) request
            );
            case ABOUT_SIMPLE_CARD -> aboutSimpleCardService.create(
                    image,
                    localeName,
                    (CreateAboutSimpleCardRequest) request
            );
            case CONTACT_SIMPLE_CARD -> contactSimpleCardService.create(
                    image,
                    localeName,
                    (CreateContactSimpleCardRequest) request
            );
        };
    }

    public ComponentDto getDto(
            ComponentName componentName,
            int id
    ) {
        return switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.getDto(id);
            case HOME_SIMPLE_CARD -> homeSimpleCardService.getDto(id);
            case WORKS_DETAILED_CARD -> worksDetailedCardService.getDto(id);
            case ABOUT_SIMPLE_CARD -> aboutSimpleCardService.getDto(id);
            case CONTACT_SIMPLE_CARD -> contactSimpleCardService.getDto(id);
        };
    }

    public Set<HomeCarouselSectionDto> getAllHomeCarouselSectionDtos(String localeName) {
        return homeCarouselSectionService.getAllDtos(localeName);
    }

    public Set<HomeSimpleCardDto> getAllHomeSimpleCardDtos(String localeName) {
        return homeSimpleCardService.getAllDtos(localeName);
    }

    public Set<WorksDetailedCardDto> getAllWorksDetailedCardDtos(String localeName) {
        return worksDetailedCardService.getAllDtos(localeName);
    }

    public Set<AboutSimpleCardDto> getAllAboutSimpleCardDtos(String localeName) {
        return aboutSimpleCardService.getAllDtos(localeName);
    }

    public Set<ContactSimpleCardDto> getAllContactSimpleCardDtos(String localeName) {
        return contactSimpleCardService.getAllDtos(localeName);
    }

    public Image getImage(
            ComponentName componentName,
            int id,
            String imageName
    ) {
        return switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.getImage(
                    id,
                    imageName
            );
            case HOME_SIMPLE_CARD -> homeSimpleCardService.getImage(
                    id,
                    imageName
            );
            case WORKS_DETAILED_CARD -> worksDetailedCardService.getImage(
                    id,
                    imageName
            );
            case ABOUT_SIMPLE_CARD -> aboutSimpleCardService.getImage(
                    id,
                    imageName
            );
            case CONTACT_SIMPLE_CARD -> contactSimpleCardService.getImage(
                    id,
                    imageName
            );
        };
    }

    public Component update(
            ComponentName componentName,
            @Nullable MultipartFile image,
            UpdateComponentRequest request
    ) {
        return switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.update(
                    image,
                    (UpdateHomeCarouselSectionRequest) request
            );
            case HOME_SIMPLE_CARD -> homeSimpleCardService.update(
                    image,
                    (UpdateHomeSimpleCardRequest) request
            );
            case WORKS_DETAILED_CARD -> worksDetailedCardService.update(
                    image,
                    (UpdateWorksDetailedCardRequest) request
            );
            case ABOUT_SIMPLE_CARD -> aboutSimpleCardService.update(
                    image,
                    (UpdateAboutSimpleCardRequest) request
            );
            case CONTACT_SIMPLE_CARD -> contactSimpleCardService.update(
                    image,
                    (UpdateContactSimpleCardRequest) request
            );
        };
    }

    public void delete(
            ComponentName componentName,
            int id
    ) {
        switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.delete(id);
            case HOME_SIMPLE_CARD -> homeSimpleCardService.delete(id);
            case WORKS_DETAILED_CARD -> worksDetailedCardService.delete(id);
            case ABOUT_SIMPLE_CARD -> aboutSimpleCardService.delete(id);
            case CONTACT_SIMPLE_CARD -> contactSimpleCardService.delete(id);
        }
    }
}
