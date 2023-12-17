package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.converter.page.component.WorksDetailedCardDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.component.CreateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateWorksDetailedCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.WorksDetailedCardDto;
import com.gungorefe.simpleportfolio.entity.page.Works;
import com.gungorefe.simpleportfolio.entity.page.component.WorksDetailedCard;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.WorksRepository;
import com.gungorefe.simpleportfolio.repository.page.component.WorksDetailedCardRepository;
import com.gungorefe.simpleportfolio.service.ImageService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class WorksDetailedCardService {
    private final ImageService imageService;
    private final WorksRepository worksRepository;
    private final WorksDetailedCardRepository repository;
    private final WorksDetailedCardDtoConverter dtoConverter;

    public WorksDetailedCard create(
            MultipartFile image,
            String localeName,
            CreateWorksDetailedCardRequest request
    ) {
        LocaleName.validate(localeName);

        String imageName = imageService.save(
                image,
                null
        );
        Works works = worksRepository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.WORKS));
        WorksDetailedCard worksDetailedCard = dtoConverter.convertToWorksDetailedCard(
                imageName,
                request,
                works
        );

        return repository.save(worksDetailedCard);
    }

    public WorksDetailedCardDto getDto(int id) {
        WorksDetailedCard worksDetailedCard = repository.findById(id).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.WORKS_DETAILED_CARD,
                id
        ));

        return dtoConverter.convertToWorksDetailedCardDto(worksDetailedCard);
    }

    public Set<WorksDetailedCardDto> getAllDtos(String localeName) {
        LocaleName.validate(localeName);

        return dtoConverter.convertToWorksDetailedCardDtoSet(repository.findAllByLocale_Name(localeName));
    }

    public Image getImage(
            int id,
            String imageName
    ) {
        String currentImageName = repository.findImageNameById(id);

        if (!currentImageName.equals(imageName)) {
            throw ExceptionFactory.getImageNotFoundException(imageName);
        }

        return imageService.get(imageName);
    }

    public WorksDetailedCard update(
            @Nullable MultipartFile image,
            UpdateWorksDetailedCardRequest request
    ) {
        WorksDetailedCard worksDetailedCard = repository.findIdAndImageNameAndWorksAndLocaleById(request.id()).orElseThrow(() ->
                ExceptionFactory.getComponentNotFoundException(
                        ComponentName.WORKS_DETAILED_CARD,
                        request.id()
                ));
        String currentImageName = worksDetailedCard.getImageName();
        String imageName = image == null
                ? currentImageName
                : imageService.save(image, currentImageName);
        WorksDetailedCard newWorksDetailedCard = dtoConverter.convertToWorksDetailedCard(
                worksDetailedCard,
                imageName,
                request
        );

        return repository.save(newWorksDetailedCard);
    }

    public void delete(int id) {
        WorksDetailedCard worksDetailedCard = repository.findIdAndImageNameById(id).orElseThrow(() -> ExceptionFactory
                .getComponentNotFoundException(
                        ComponentName.WORKS_DETAILED_CARD,
                        id
                ));

        repository.delete(worksDetailedCard);
        imageService.delete(worksDetailedCard.getImageName());
    }
}
