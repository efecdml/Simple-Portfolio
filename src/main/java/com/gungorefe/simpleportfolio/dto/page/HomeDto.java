package com.gungorefe.simpleportfolio.dto.page;

import com.gungorefe.simpleportfolio.dto.page.component.HomeCarouselSectionDto;

import java.util.List;

public record HomeDto(
        String title,
        String text,
        String secondTitle,
        String secondText,
        List<HomeCarouselSectionDto> carouselSections
) implements PageDto {
}
