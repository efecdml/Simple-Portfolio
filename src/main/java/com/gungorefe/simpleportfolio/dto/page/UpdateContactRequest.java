package com.gungorefe.simpleportfolio.dto.page;

public record UpdateContactRequest(
        String title,
        String text,
        String email,
        String location,
        String workingDays,
        String workingHours,
        String googleMapsCoordination
) implements UpdatePageRequest {
}
