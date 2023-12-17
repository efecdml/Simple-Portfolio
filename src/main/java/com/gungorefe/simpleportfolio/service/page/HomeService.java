package com.gungorefe.simpleportfolio.service.page;

import com.gungorefe.simpleportfolio.dto.converter.page.HomeDtoConverter;
import com.gungorefe.simpleportfolio.dto.page.HomeDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateHomeRequest;
import com.gungorefe.simpleportfolio.entity.page.Home;
import com.gungorefe.simpleportfolio.exception.ExceptionFactory;
import com.gungorefe.simpleportfolio.repository.page.HomeRepository;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HomeService {
    private final HomeRepository repository;
    private final HomeDtoConverter dtoConverter;

    public HomeDto getDto(String localeName) {
        Home home = repository.findWithCarouselSectionsByLocale_Name(localeName).orElseThrow(() -> ExceptionFactory
                .getPageNotFoundException(PageName.HOME));

        return dtoConverter.convertToHomeDto(home);
    }

    public Home update(
            String localeName,
            UpdateHomeRequest request
    ) {
        Home home = repository.findIdAndLocaleIdByLocale_Name(localeName).orElseThrow(() ->
                ExceptionFactory.getPageNotFoundException(PageName.HOME));
        Home newHome = dtoConverter.convertToHome(
                home,
                request
        );

        return repository.save(newHome);
    }
}
