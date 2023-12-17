package com.gungorefe.simpleportfolio.dto.converter.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.Home;
import com.gungorefe.simpleportfolio.entity.page.component.HomeSimpleCard;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HomeSimpleCardDtoConverter {
    public HomeSimpleCardDto convertToHomeSimpleCardDto(HomeSimpleCard homeSimpleCard) {
        HomeSimpleCardDto dto = new HomeSimpleCardDto(
                homeSimpleCard.getId(),
                homeSimpleCard.getImageName(),
                homeSimpleCard.getTitle(),
                homeSimpleCard.getText(),
                homeSimpleCard.getDisplayOrder()
        );

        return dto;
    }

    public List<HomeSimpleCardDto> convertToHomeSimpleCardDtoList(Collection<HomeSimpleCard> homeSimpleCards) {
        List<HomeSimpleCardDto> dtos = homeSimpleCards.stream()
                .map(this::convertToHomeSimpleCardDto)
                .toList();

        return dtos;
    }

    public Set<HomeSimpleCardDto> convertToHomeSimpleCardDtoSet(Collection<HomeSimpleCard> homeSimpleCards) {
        Set<HomeSimpleCardDto> dtos = homeSimpleCards.stream()
                .map(this::convertToHomeSimpleCardDto)
                .collect(Collectors.toSet());

        return dtos;
    }

    public HomeSimpleCard convertToHomeSimpleCard(
            String imageName,
            CreateHomeSimpleCardRequest request,
            Home home
    ) {
        HomeSimpleCard homeSimpleCard = new HomeSimpleCard(
                imageName,
                request.title(),
                request.text(),
                request.displayOrder(),
                home,
                home.getLocale()
        );

        return homeSimpleCard;
    }

    public HomeSimpleCard convertToHomeSimpleCard(
            HomeSimpleCard homeSimpleCard,
            String imageName,
            UpdateHomeSimpleCardRequest request
    ) {
        homeSimpleCard.setImageName(imageName);
        homeSimpleCard.setTitle(request.title());
        homeSimpleCard.setText(request.text());
        homeSimpleCard.setDisplayOrder(request.displayOrder());

        return homeSimpleCard;
    }
}
