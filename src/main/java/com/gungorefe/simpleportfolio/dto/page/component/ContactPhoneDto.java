package com.gungorefe.simpleportfolio.dto.page.component;

public record ContactPhoneDto(
        int id,
        String tag,
        String number,
        int displayOrder
) implements ComponentDto {
}
