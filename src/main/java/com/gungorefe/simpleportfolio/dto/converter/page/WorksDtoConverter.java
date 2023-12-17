package com.gungorefe.simpleportfolio.dto.converter.page;

import com.gungorefe.simpleportfolio.dto.converter.page.component.WorksDetailedCardDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.UpdateWorksRequest;
import com.gungorefe.simpleportfolio.dto.page.WorksDto;
import com.gungorefe.simpleportfolio.entity.page.Works;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WorksDtoConverter {
    private final WorksDetailedCardDtoConverter worksDetailedCardDtoConverter;

    public WorksDto convertToWorksDto(Works works) {
        WorksDto dto = new WorksDto(
                works.getImageName(),
                works.getTitle(),
                works.getText(),
                worksDetailedCardDtoConverter.convertToWorksDetailedCardDtoList(works.getWorksDetailedCards())
        );

        return dto;
    }

    public Works convertToWorks(
            Works works,
            String imageName,
            UpdateWorksRequest request
    ) {
        works.setImageName(imageName);
        works.setTitle(request.title());
        works.setText(request.text());

        return works;
    }
}
