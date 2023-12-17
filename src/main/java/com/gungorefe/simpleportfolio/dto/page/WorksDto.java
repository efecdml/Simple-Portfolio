package com.gungorefe.simpleportfolio.dto.page;

import com.gungorefe.simpleportfolio.dto.page.component.WorksDetailedCardDto;

import java.util.List;

public record WorksDto(
        String imageName,
        String title,
        String text,
        List<WorksDetailedCardDto> detailedCards
) implements PageDto {
}
