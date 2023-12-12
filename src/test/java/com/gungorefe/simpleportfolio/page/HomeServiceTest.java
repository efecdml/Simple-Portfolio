package com.gungorefe.simpleportfolio.page;

import com.gungorefe.simpleportfolio.dto.page.HomeDto;
import com.gungorefe.simpleportfolio.dto.page.PageDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateHomeRequest;
import com.gungorefe.simpleportfolio.entity.page.Page;
import com.gungorefe.simpleportfolio.service.page.PageService;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import com.gungorefe.simpleportfolio.vo.PageName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")
@Sql(scripts = {"/home-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class HomeServiceTest {
    @Autowired
    private PageService service;

    @Test
    public void givenHomePageNameAndEnglishLocaleName_shouldReturnHomeDto() {
        PageDto dto = service.getDto(
                LocaleName.TURKISH.value,
                PageName.HOME
        );

        assertEquals(dto.getClass(), HomeDto.class);
    }

    @Test
    public void givenUpdateHomeRequest_shouldUpdateHome() {
        String localeName = LocaleName.ENGLISH.value;
        PageName pageName = PageName.HOME;

        Page current = service.getDto(
                localeName,
                pageName
        );
        UpdateHomeRequest request = new UpdateHomeRequest(
                "new title",
                "new text",
                "new second title",
                "new second text"
        );
        Page updated = service.update(
                localeName,
                pageName,
                request
        );

        assertNotEquals(updated, current);
    }
}
