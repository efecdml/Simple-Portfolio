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
        };
    }

    public ComponentDto getDto(
            ComponentName componentName,
            int id
    ) {
        return switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.getDto(id);
            case HOME_SIMPLE_CARD -> homeSimpleCardService.getDto(id);
        };
    }

    public Set<HomeCarouselSectionDto> getAllHomeCarouselSectionDtos(String localeName) {
        return homeCarouselSectionService.getAllDtos(localeName);
    }

    public Set<HomeSimpleCardDto> getAllHomeSimpleCardDtos(String localeName) {
        return homeSimpleCardService.getAllDtos(localeName);
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
        };
    }

    public void delete(
            ComponentName componentName,
            int id
    ) {
        switch (ComponentName.get(componentName)) {
            case HOME_CAROUSEL_SECTION -> homeCarouselSectionService.delete(id);
            case HOME_SIMPLE_CARD -> homeSimpleCardService.delete(id);
        }
    }
}