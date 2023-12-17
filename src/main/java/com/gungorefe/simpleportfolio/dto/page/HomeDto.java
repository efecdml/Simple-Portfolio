package com.gungorefe.simpleportfolio.dto.page;

import com.gungorefe.simpleportfolio.dto.page.component.HomeCarouselSectionDto;
import com.gungorefe.simpleportfolio.dto.page.component.HomeSimpleCardDto;

import java.util.List;

public record HomeDto(
        String title,
        String text,
        String secondTitle,
        String secondText,
        List<HomeCarouselSectionDto> carouselSections,
        List<HomeSimpleCardDto> simpleCards
) implements PageDto {
}
