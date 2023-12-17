package com.gungorefe.simpleportfolio.service.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.converter.page.component.HomeCarouselSectionDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.component.CreateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.dto.page.component.HomeCarouselSectionDto;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateHomeCarouselSectionRequest;
import com.gungorefe.simpleportfolio.entity.page.Home;
import com.gungorefe.simpleportfolio.entity.page.component.HomeCarouselSection;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.HomeRepository;
import com.gungorefe.simpleportfolio.repository.page.component.HomeCarouselSectionRepository;
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
public class HomeCarouselSectionService {
    private final ImageService imageService;
    private final HomeRepository homeRepository;
    private final HomeCarouselSectionRepository repository;
    private final HomeCarouselSectionDtoConverter dtoConverter;

    public HomeCarouselSection create(
            MultipartFile image,
            String localeName,
            CreateHomeCarouselSectionRequest request
    ) {
        LocaleName.validate(localeName);

        String imageName = imageService.save(
                image,
                null
        );
        Home home = homeRepository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.HOME));
        HomeCarouselSection homeCarouselSection = dtoConverter.convertToHomeCarouselSection(
                imageName,
                request,
                home
        );

        return repository.save(homeCarouselSection);
    }

    public HomeCarouselSectionDto getDto(int id) {
        HomeCarouselSection homeCarouselSection = repository.findById(id).orElseThrow(() -> ExceptionFactory.getComponentNotFoundException(
                ComponentName.HOME_CAROUSEL_SECTION,
                id
        ));

        return dtoConverter.convertToHomeCarouselSectionDto(homeCarouselSection);
    }

    public Set<HomeCarouselSectionDto> getAllDtos(String localeName) {
        LocaleName.validate(localeName);

        return dtoConverter.convertToHomeCarouselSectionDtoSet(repository.findAllByLocale_Name(localeName));
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

    public HomeCarouselSection update(
            @Nullable MultipartFile image,
            UpdateHomeCarouselSectionRequest request
    ) {
        HomeCarouselSection homeCarouselSection = repository.findIdAndImageNameAndHomeAndLocaleById(request.id()).orElseThrow(() ->
                ExceptionFactory.getComponentNotFoundException(
                        ComponentName.HOME_CAROUSEL_SECTION,
                        request.id()
                ));
        String currentImageName = homeCarouselSection.getImageName();
        String imageName = image == null
                ? currentImageName
                : imageService.save(image, currentImageName);
        HomeCarouselSection newHomeCarouselSection = dtoConverter.convertToHomeCarouselSection(
                homeCarouselSection,
                imageName,
                request
        );

        return repository.save(newHomeCarouselSection);
    }

    public void delete(int id) {
        HomeCarouselSection homeCarouselSection = repository.findIdAndImageNameById(id).orElseThrow(() -> ExceptionFactory
                .getComponentNotFoundException(
                        ComponentName.HOME_CAROUSEL_SECTION,
                        id
                ));

        repository.delete(homeCarouselSection);
        imageService.delete(homeCarouselSection.getImageName());
    }
}
