package com.gungorefe.simpleportfolio.dto.page;

import com.gungorefe.simpleportfolio.dto.page.component.AboutSimpleCardDto;

import java.util.List;

public record AboutDto(
        String imageName,
        String title,
        String text,
        List<AboutSimpleCardDto> simpleCards
) implements PageDto {
}
