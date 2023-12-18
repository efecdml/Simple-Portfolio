package com.gungorefe.simpleportfolio.dto.page;

public record ContactDto(
        String title,
        String text,
        String email,
        String location,
        String workingDays,
        String workingHours,
        String googleMapsCoordination
) implements PageDto {
}
