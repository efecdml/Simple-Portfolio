package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.converter.page.component.HomeSimpleCardDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.Home;
import com.gungorefe.simpleportfolio.entity.page.component.HomeSimpleCard;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.HomeRepository;
import com.gungorefe.simpleportfolio.repository.page.component.HomeSimpleCardRepository;
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
public class HomeSimpleCardService {
    private final ImageService imageService;
    private final HomeRepository homeRepository;
    private final HomeSimpleCardRepository repository;
    private final HomeSimpleCardDtoConverter dtoConverter;

    public HomeSimpleCard create(
            MultipartFile image,
            String localeName,
            CreateHomeSimpleCardRequest request
    ) {
        String imageName = imageService.save(
                image,
                null
        );
        Home home = homeRepository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.HOME));
        HomeSimpleCard homeSimpleCard = dtoConverter.convertToHomeSimpleCard(
                imageName,
                request,
                home
        );

        return repository.save(homeSimpleCard);
    }

    public HomeSimpleCardDto getDto(int id) {
        HomeSimpleCard homeSimpleCard = repository.findById(id).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.HOME_SIMPLE_CARD,
                id
        ));

        return dtoConverter.convertToHomeSimpleCardDto(homeSimpleCard);
    }

    public Set<HomeSimpleCardDto> getAllDtos(String localeName) {
        LocaleName.validate(localeName);

        return dtoConverter.convertToHomeSimpleCardDtoSet(repository.findAllByLocale_Name(localeName));
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

    public HomeSimpleCard update(
            @Nullable MultipartFile image,
            UpdateHomeSimpleCardRequest request,
            String localeName
    ) {
        HomeSimpleCard homeSimpleCard = repository.findIdAndImageNameAndHomeAndLocaleByIdAndLocale_Name(
                request.id(),
                localeName
        ).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.HOME_SIMPLE_CARD,
                request.id()
        ));
        String currentImageName = homeSimpleCard.getImageName();
        String imageName = image == null
                ? currentImageName
                : imageService.save(image, currentImageName);
        HomeSimpleCard newHomeSimpleCard = dtoConverter.convertToHomeSimpleCard(
                homeSimpleCard,
                imageName,
                request
        );

        return repository.save(newHomeSimpleCard);
    }

    public void delete(
            int id,
            String localeName
    ) {
        HomeSimpleCard homeSimpleCard = repository.findIdAndImageNameByIdAndLocale_Name(
                id,
                localeName
        ).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.HOME_SIMPLE_CARD,
                id
        ));

        repository.delete(homeSimpleCard);
        imageService.delete(homeSimpleCard.getImageName());
    }
}
