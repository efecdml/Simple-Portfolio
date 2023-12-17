package com.gungorefe.simpleportfolio.page;

import com.gungorefe.simpleportfolio.dto.page.PageDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateWorksRequest;
import com.gungorefe.simpleportfolio.dto.page.WorksDto;
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
@Sql(scripts = {"/works-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class WorksServiceTest {
    @Autowired
    private PageService service;

    @Test
    public void givenWorksPageNameAndEnglishLocaleName_shouldReturnWorksDto() {
        PageDto dto = service.getDto(
                LocaleName.ENGLISH.value,
                PageName.WORKS
        );

        assertEquals(dto.getClass(), WorksDto.class);
    }

    @Test
    public void givenUpdateWorksRequest_shouldUpdateWorks() {
        String localeName = LocaleName.ENGLISH.value;
        PageName pageName = PageName.WORKS;

        Page current = service.getDto(
                localeName,
                pageName
        );
        UpdateWorksRequest request = new UpdateWorksRequest(
                "new title",
                "new text"
        );
        Page updated = service.update(
                localeName,
                pageName,
                request,
                null
        );

        assertNotEquals(updated, current);
    }
}
