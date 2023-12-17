package com.gungorefe.simpleportfolio.dto.converter.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeCarouselSectionDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.entity.page.Home;
import com.gungorefe.simpleportfolio.entity.page.component.HomeCarouselSection;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HomeCarouselSectionDtoConverter {
    public HomeCarouselSectionDto convertToHomeCarouselSectionDto(HomeCarouselSection homeCarouselSection) {
        HomeCarouselSectionDto dto = new HomeCarouselSectionDto(
                homeCarouselSection.getId(),
                homeCarouselSection.getImageName(),
                homeCarouselSection.getText(),
                homeCarouselSection.getDisplayOrder()
        );

        return dto;
    }

    public List<HomeCarouselSectionDto> convertToHomeCarouselSectionDtoList(Collection<HomeCarouselSection> homeCarouselSections) {
        List<HomeCarouselSectionDto> dtos = homeCarouselSections.stream()
                .map(this::convertToHomeCarouselSectionDto)
                .toList();

        return dtos;
    }

    public Set<HomeCarouselSectionDto> convertToHomeCarouselSectionDtoSet(Collection<HomeCarouselSection> homeCarouselSections) {
        Set<HomeCarouselSectionDto> dtos = homeCarouselSections.stream()
                .map(this::convertToHomeCarouselSectionDto)
                .collect(Collectors.toSet());

        return dtos;
    }

    public HomeCarouselSection convertToHomeCarouselSection(
            String imageName,
            CreateHomeCarouselSectionRequest request,
            Home home
    ) {
        HomeCarouselSection homeCarouselSection = new HomeCarouselSection(
                imageName,
                request.text(),
                request.displayOrder(),
                home,
                home.getLocale()
        );

        return homeCarouselSection;
    }

    public HomeCarouselSection convertToHomeCarouselSection(
            HomeCarouselSection homeCarouselSection,
            String imageName,
            UpdateHomeCarouselSectionRequest request
    ) {
        homeCarouselSection.setImageName(imageName);
        homeCarouselSection.setText(request.text());
        homeCarouselSection.setDisplayOrder(request.displayOrder());

        return homeCarouselSection;
    }
}
