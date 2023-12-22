package com.gungorefe.simpleportfolio.dto.page.component;

public record UpdateContactPhoneRequest(
        int id,
        String tag,
        String number,
        int displayOrder
) implements UpdateComponentRequest {
}
