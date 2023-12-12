package com.gungorefe.simpleportfolio.service.page;

import com.gungorefe.simpleportfolio.dto.page.PageDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateHomeRequest;
import com.gungorefe.simpleportfolio.dto.page.UpdatePageRequest;
import com.gungorefe.simpleportfolio.entity.page.Page;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PageService {
    private final HomeService homeService;

    public PageDto getDto(
            String localeName,
            PageName pageName
    ) {
        LocaleName.validate(localeName);

        return switch (PageName.get(pageName)) {
            case HOME -> homeService.getDto(localeName);
        };
    }

    public Page update(
            String localeName,
            PageName pageName,
            UpdatePageRequest request
    ) {
        LocaleName.validate(localeName);

        return switch (PageName.get(pageName)) {
            case HOME -> homeService.update(
                    localeName,
                    (UpdateHomeRequest) request
            );
        };
    }
}
