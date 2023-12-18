package com.gungorefe.simpleportfolio.dto.converter.page;

import com.gungorefe.simpleportfolio.dto.converter.page.component.AboutSimpleCardDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.AboutDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateAboutRequest;
import com.gungorefe.simpleportfolio.entity.page.About;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AboutDtoConverter {
    private final AboutSimpleCardDtoConverter aboutSimpleCardDtoConverter;

    public AboutDto convertToAboutDto(About about) {
        AboutDto dto = new AboutDto(
                about.getImageName(),
                about.getTitle(),
                about.getText(),
                aboutSimpleCardDtoConverter.convertToAboutSimpleCardDtoList(about.getAboutSimpleCards())
        );

        return dto;
    }

    public About convertToAbout(
            About about,
            String imageName,
            UpdateAboutRequest request
    ) {
        about.setImageName(imageName);
        about.setTitle(request.title());
        about.setText(request.text());

        return about;
    }
}
