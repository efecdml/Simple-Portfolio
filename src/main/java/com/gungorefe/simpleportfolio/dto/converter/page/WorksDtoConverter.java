package com.gungorefe.simpleportfolio.dto.converter.page;

import com.gungorefe.simpleportfolio.dto.page.UpdateWorksRequest;
import com.gungorefe.simpleportfolio.dto.page.WorksDto;
import com.gungorefe.simpleportfolio.entity.page.Works;
import org.springframework.stereotype.Component;

@Component
public class WorksDtoConverter {
    public WorksDto convertToWorksDto(Works works) {
        WorksDto dto = new WorksDto(
                works.getImageName(),
                works.getTitle(),
                works.getText()
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
