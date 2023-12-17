package com.gungorefe.simpleportfolio.service.page;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.converter.page.WorksDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.UpdateWorksRequest;
import com.gungorefe.simpleportfolio.dto.page.WorksDto;
import com.gungorefe.simpleportfolio.entity.page.Works;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.WorksRepository;
import com.gungorefe.simpleportfolio.service.ImageService;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class WorksService {
    private final ImageService imageService;
    private final WorksRepository repository;
    private final WorksDtoConverter dtoConverter;

    public WorksDto getDto(String localeName) {
        Works works = repository.findWithWorksDetailedCardByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.WORKS));

        return dtoConverter.convertToWorksDto(works);
    }

    public Image getImage(
            String localeName,
            String imageName
    ) {
        String currentImageName = repository.findImageNameByLocale_Name(localeName);

        if (!currentImageName.equals(imageName)) {
            throw ExceptionFactory.getImageNotFoundException(imageName);
        }

        return imageService.get(imageName);
    }

    public Works update(
            String localeName,
            @Nullable MultipartFile image,
            UpdateWorksRequest request
    ) {
        Works works = repository.findIdAndImageNameAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.WORKS));
        String currentImageName = works.getImageName();
        String imageName = image == null
                ? currentImageName
                : imageService.save(image, currentImageName);
        Works newWorks = dtoConverter.convertToWorks(
                works,
                imageName,
                request
        );

        return repository.save(newWorks);
    }
}
