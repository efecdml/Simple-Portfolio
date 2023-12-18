package com.gungorefe.simpleportfolio.dto.page;

import com.gungorefe.simpleportfolio.dto.page.component.ContactSimpleCardDto;

import java.util.List;

public record ContactDto(
        String title,
        String text,
        String email,
        String location,
        String workingDays,
        String workingHours,
        String googleMapsCoordination,
        List<ContactSimpleCardDto> simpleCards
) implements PageDto {
}
