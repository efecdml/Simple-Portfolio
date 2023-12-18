package com.gungorefe.simpleportfolio.dto.converter.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.AboutSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateAboutSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.About;
import com.gungorefe.simpleportfolio.entity.page.component.AboutSimpleCard;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AboutSimpleCardDtoConverter {
    public AboutSimpleCardDto convertToAboutSimpleCardDto(AboutSimpleCard aboutSimpleCard) {
        AboutSimpleCardDto dto = new AboutSimpleCardDto(
                aboutSimpleCard.getId(),
                aboutSimpleCard.getImageName(),
                aboutSimpleCard.getTitle(),
                aboutSimpleCard.getText(),
                aboutSimpleCard.getDisplayOrder()
        );

        return dto;
    }

    public List<AboutSimpleCardDto> convertToAboutSimpleCardDtoList(Collection<AboutSimpleCard> aboutSimpleCards) {
        List<AboutSimpleCardDto> dtos = aboutSimpleCards.stream()
                .map(this::convertToAboutSimpleCardDto)
                .toList();

        return dtos;
    }

    public Set<AboutSimpleCardDto> convertToAboutSimpleCardDtoSet(Collection<AboutSimpleCard> aboutSimpleCards) {
        Set<AboutSimpleCardDto> dtos = aboutSimpleCards.stream()
                .map(this::convertToAboutSimpleCardDto)
                .collect(Collectors.toSet());

        return dtos;
    }

    public AboutSimpleCard convertToAboutSimpleCard(
            String imageName,
            CreateAboutSimpleCardRequest request,
            About about
    ) {
        AboutSimpleCard aboutSimpleCard = new AboutSimpleCard(
                imageName,
                request.title(),
                request.text(),
                request.displayOrder(),
                about,
                about.getLocale()
        );

        return aboutSimpleCard;
    }

    public AboutSimpleCard convertToAboutSimpleCard(
            AboutSimpleCard aboutSimpleCard,
            String imageName,
            UpdateAboutSimpleCardRequest request
    ) {
        aboutSimpleCard.setImageName(imageName);
        aboutSimpleCard.setTitle(request.title());
        aboutSimpleCard.setText(request.text());
        aboutSimpleCard.setDisplayOrder(request.displayOrder());

        return aboutSimpleCard;
    }
}
