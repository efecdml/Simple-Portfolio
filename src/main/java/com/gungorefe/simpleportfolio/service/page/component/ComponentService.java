package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.*;
import com.gungorefe.simpleportfolio.entity.page.component.Component;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    private final ContactPhoneService contactPhoneService;

    @CacheEvict(
            value = "pages",
            key = "#pageName.value+#localeName"
    )
    public Component create(
            ComponentName componentName,
            @Nullable MultipartFile image,
            CreateComponentRequest request,
            @Nullable String localeName,
            PageName pageName
    ) {
        LocaleName.validate(localeName);

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
            case CONTACT_PHONE -> contactPhoneService.create(
                    localeName,
                    (CreateContactPhoneRequest) request
            );
        };
    }

    @Cacheable(
            value = "components",
            key = "#componentName.value+#id"
    )
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
            case CONTACT_PHONE -> contactPhoneService.getDto(id);
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

    public Set<ContactPhoneDto> getAllContactPhoneDtos(String localeName) {
        return contactPhoneService.getAllDtos(localeName);
    }

    @Cacheable(
            value = "componentImages",
            key = "#componentName.value+#id"
    )
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
            case CONTACT_PHONE -> null;
        };
    }

    @Caching(evict = {
            @CacheEvict(
                    value = "components",
                    key = "#componentName.value+#request.id"
            ),
            @CacheEvict(
                    value = "componentImages",
                    key = "#componentName.value+#request.id"
            ),
            @CacheEvict(
                    value = "pages",
                    key = "#pageName.value+#localeName"
            )
    })
    public Component update(
            ComponentName componentName,
            @Nullable MultipartFile image,
            UpdateComponentRequest request,
            PageName pageName,
            String localeName
    ) {
        LocaleName.validate(localeName);

        return switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.update(
                    image,
                    (UpdateHomeCarouselSectionRequest) request,
                    localeName
            );
            case HOME_SIMPLE_CARD -> homeSimpleCardService.update(
                    image,
                    (UpdateHomeSimpleCardRequest) request,
                    localeName
            );
            case WORKS_DETAILED_CARD -> worksDetailedCardService.update(
                    image,
                    (UpdateWorksDetailedCardRequest) request,
                    localeName
            );
            case ABOUT_SIMPLE_CARD -> aboutSimpleCardService.update(
                    image,
                    (UpdateAboutSimpleCardRequest) request,
                    localeName
            );
            case CONTACT_SIMPLE_CARD -> contactSimpleCardService.update(
                    image,
                    (UpdateContactSimpleCardRequest) request,
                    localeName
            );
            case CONTACT_PHONE -> contactPhoneService.update(
                    (UpdateContactPhoneRequest) request,
                    localeName
            );
        };
    }

    @Caching(evict = {
            @CacheEvict(
                    value = "components",
                    key = "#componentName.value+#id"
            ),
            @CacheEvict(
                    value = "componentImages",
                    key = "#componentName.value+#id"
            ),
            @CacheEvict(
                    value = "pages",
                    key = "#pageName.value+#localeName"
            )
    })
    public void delete(
            ComponentName componentName,
            int id,
            PageName pageName,
            String localeName
    ) {
        LocaleName.validate(localeName);

        switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.delete(
                    id,
                    localeName
            );
            case HOME_SIMPLE_CARD -> homeSimpleCardService.delete(
                    id,
                    localeName
            );
            case WORKS_DETAILED_CARD -> worksDetailedCardService.delete(
                    id,
                    localeName
            );
            case ABOUT_SIMPLE_CARD -> aboutSimpleCardService.delete(
                    id,
                    localeName
            );
            case CONTACT_SIMPLE_CARD -> contactSimpleCardService.delete(
                    id,
                    localeName
            );
            case CONTACT_PHONE -> contactPhoneService.delete(
                    id,
                    localeName
            );
        }
    }
}
