package com.gungorefe.simpleportfolio.service.page;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.converter.page.AboutDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.AboutDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateAboutRequest;
import com.gungorefe.simpleportfolio.entity.page.About;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.AboutRepository;
import com.gungorefe.simpleportfolio.service.ImageService;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AboutService {
    private final ImageService imageService;
    private final AboutRepository repository;
    private final AboutDtoConverter dtoConverter;

    public AboutDto getDto(String localeName) {
        About about = repository.findWithSimpleCardByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.ABOUT));

        return dtoConverter.convertToAboutDto(about);
    }

    public Image getImage(
            String localeName,
            String imageName
    ) {
        String currentImageName = repository.findImageNameByLocale_Name(localeName);

        if (!imageName.equals(currentImageName)) {
            throw ExceptionFactory.getImageNotFoundException(imageName);
        }

        return imageService.get(imageName);
    }

    public About update(
            String localeName,
            @Nullable MultipartFile image,
            UpdateAboutRequest request
    ) {
        About about = repository.findIdAndImageNameAndLocaleIdByLocale_Name(localeName).orElseThrow(() ->
                ExceptionFactory.getPageNotFoundException(PageName.ABOUT));
        String currentImageName = about.getImageName();
        String imageName = image == null
                ? currentImageName
                : imageService.save(image, currentImageName);
        About newAbout = dtoConverter.convertToAbout(
                about,
                imageName,
                request
        );

        return repository.save(newAbout);
    }
}
