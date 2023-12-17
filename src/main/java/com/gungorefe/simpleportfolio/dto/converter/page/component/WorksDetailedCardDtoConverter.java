package com.gungorefe.simpleportfolio.dto.converter.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.CreateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.WorksDetailedCardDto;
import com.gungorefe.simpleportfolio.entity.page.Works;
import com.gungorefe.simpleportfolio.entity.page.component.WorksDetailedCard;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WorksDetailedCardDtoConverter {
    public WorksDetailedCardDto convertToWorksDetailedCardDto(WorksDetailedCard worksDetailedCard) {
        WorksDetailedCardDto dto = new WorksDetailedCardDto(
                worksDetailedCard.getId(),
                worksDetailedCard.getImageName(),
                worksDetailedCard.getTitle(),
                worksDetailedCard.getText(),
                worksDetailedCard.getDisplayOrder()
        );

        return dto;
    }

    public List<WorksDetailedCardDto> convertToWorksDetailedCardDtoList(Collection<WorksDetailedCard> worksDetailedCards) {
        List<WorksDetailedCardDto> dtos = worksDetailedCards.stream()
                .map(this::convertToWorksDetailedCardDto)
                .toList();

        return dtos;
    }

    public Set<WorksDetailedCardDto> convertToWorksDetailedCardDtoSet(Collection<WorksDetailedCard> worksDetailedCards) {
        Set<WorksDetailedCardDto> dtos = worksDetailedCards.stream()
                .map(this::convertToWorksDetailedCardDto)
                .collect(Collectors.toSet());

        return dtos;
    }

    public WorksDetailedCard convertToWorksDetailedCard(
            String imageName,
            CreateWorksDetailedCardRequest request,
            Works works
    ) {
        WorksDetailedCard worksDetailedCard = new WorksDetailedCard(
                imageName,
                request.title(),
                request.text(),
                request.displayOrder(),
                works,
                works.getLocale()
        );

        return worksDetailedCard;
    }

    public WorksDetailedCard convertToWorksDetailedCard(
            WorksDetailedCard worksDetailedCard,
            String imageName,
            UpdateWorksDetailedCardRequest request
    ) {
        worksDetailedCard.setImageName(imageName);
        worksDetailedCard.setTitle(request.title());
        worksDetailedCard.setText(request.text());
        worksDetailedCard.setDisplayOrder(request.displayOrder());

        return worksDetailedCard;
    }
}
