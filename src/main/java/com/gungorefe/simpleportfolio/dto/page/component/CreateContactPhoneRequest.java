package com.gungorefe.simpleportfolio.dto.page.component;

public record CreateContactPhoneRequest(
        String tag,
        String number,
        int displayOrder
) implements CreateComponentRequest {
}
