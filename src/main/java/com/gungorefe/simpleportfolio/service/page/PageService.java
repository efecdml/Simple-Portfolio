package com.gungorefe.simpleportfolio.service.page;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.PageDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateHomeRequest;
import com.gungorefe.simpleportfolio.dto.page.UpdatePageRequest;
import com.gungorefe.simpleportfolio.dto.page.UpdateWorksRequest;
import com.gungorefe.simpleportfolio.entity.page.Page;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class PageService {
    private final HomeService homeService;
    private final WorksService worksService;

    public PageDto getDto(
            String localeName,
            PageName pageName
    ) {
        LocaleName.validate(localeName);

        return switch (PageName.get(pageName)) {
            case HOME -> homeService.getDto(localeName);
            case WORKS -> worksService.getDto(localeName);
        };
    }

    public Image getImage(
            PageName pageName,
            String localeName,
            String imageName
    ) {
        return switch (PageName.get(pageName)) {
            case HOME -> null;
            case WORKS -> worksService.getImage(
                    localeName,
                    imageName
            );
        };
    }

    public Page update(
            String localeName,
            PageName pageName,
            UpdatePageRequest request,
            @Nullable MultipartFile image
    ) {
        LocaleName.validate(localeName);

        return switch (PageName.get(pageName)) {
            case HOME -> homeService.update(
                    localeName,
                    (UpdateHomeRequest) request
            );
            case WORKS -> worksService.update(
                    localeName,
                    image,
                    (UpdateWorksRequest) request
            );
        };
    }
}
