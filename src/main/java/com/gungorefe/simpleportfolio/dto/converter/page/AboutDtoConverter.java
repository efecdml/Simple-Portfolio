package com.gungorefe.simpleportfolio.dto.converter.page;

import com.gungorefe.simpleportfolio.dto.page.AboutDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateAboutRequest;
import com.gungorefe.simpleportfolio.entity.page.About;
import org.springframework.stereotype.Component;

@Component
public class AboutDtoConverter {
    public AboutDto convertToAboutDto(About about) {
        AboutDto dto = new AboutDto(
                about.getImageName(),
                about.getTitle(),
                about.getText()
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
