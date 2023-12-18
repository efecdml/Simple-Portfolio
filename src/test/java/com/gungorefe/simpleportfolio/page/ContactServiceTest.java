package com.gungorefe.simpleportfolio.page;

import com.gungorefe.simpleportfolio.dto.page.ContactDto;
import com.gungorefe.simpleportfolio.dto.page.PageDto;
import com.gungorefe.simpleportfolio.dto.page.UpdateContactRequest;
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
@Sql(scripts = {"/contact-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class ContactServiceTest {
    @Autowired
    private PageService service;

    @Test
    public void givenContactPageNameAndEnglishLocaleName_shouldReturnContactDto() {
        PageDto dto = service.getDto(
                LocaleName.ENGLISH.value,
                PageName.CONTACT
        );

        assertEquals(dto.getClass(), ContactDto.class);
    }

    @Test
    public void givenUpdateContactRequest_shouldUpdateContact() {
        String localeName = LocaleName.TURKISH.value;
        PageName pageName = PageName.CONTACT;

        Page current = service.getDto(
                localeName,
                pageName
        );
        UpdateContactRequest request = new UpdateContactRequest(
                "updated title",
                "updated text",
                "updated@email.com",
                "updated location",
                "tuesday-saturday",
                "08:30-17:30",
                "updatedGmapsCoordination"
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
