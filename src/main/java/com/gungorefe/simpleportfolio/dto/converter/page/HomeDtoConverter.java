package com.gungorefe.simpleportfolio.dto.converter.page;

import com.gungorefe.simpleportfolio.dto.page.HomeDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateHomeRequest;
import com.gungorefe.simpleportfolio.entity.page.Home;
import org.springframework.stereotype.Component;

@Component
public class HomeDtoConverter {
    public HomeDto convertToHomeDto(Home home) {
        HomeDto dto = new HomeDto(
                home.getTitle(),
                home.getText(),
                home.getSecondTitle(),
                home.getSecondText()
        );

        return dto;
    }

    public Home convertToHome(
            Home home,
            UpdateHomeRequest request
    ) {
        home.setTitle(request.title());
        home.setText(request.text());
        home.setSecondTitle(request.secondTitle());
        home.setSecondText(request.secondText());

        return home;
    }
}
