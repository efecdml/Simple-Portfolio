package com.gungorefe.simpleportfolio.page.component;

import com.gungorefe.simpleportfolio.dto.page.component.ComponentDto;
import com.gungorefe.simpleportfolio.dto.page.component.ContactPhoneDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactPhoneRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactPhoneRequest;
import com.gungorefe.simpleportfolio.entity.page.component.ContactPhone;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@Sql(scripts = {"/contact-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class ContactPhoneServiceTest {
    @Autowired
    private ComponentService service;
    private final int id = 1;

    @Test
    public void givenContactPhoneId1_shouldReturnContactPhoneDto() {
        ComponentDto dto = service.getDto(
                ComponentName.CONTACT_PHONE,
                id
        );

        assertEquals(dto.getClass(), ContactPhoneDto.class);
    }

    @Test
    public void givenCreateContactPhoneRequest_shouldCreateContactPhone() {
        CreateContactPhoneRequest request = new CreateContactPhoneRequest(
                "created tag",
                "4312",
                2
        );
        ContactPhone contactPhone = (ContactPhone) service.create(
                ComponentName.CONTACT_PHONE,
                null,
                request,
                LocaleName.TURKISH.value
        );

        assertEquals(request.tag(), contactPhone.getTag());
        assertEquals(request.number(), contactPhone.getNumber());
        assertEquals(request.displayOrder(), contactPhone.getDisplayOrder());
    }

    @Test
    public void givenUpdateContactPhoneRequest_shouldUpdateContactPhone() {
        UpdateContactPhoneRequest request = new UpdateContactPhoneRequest(
                id,
                "updated tag",
                "4123123",
                6
        );
        ContactPhone contactPhone = (ContactPhone) service.update(
                ComponentName.CONTACT_PHONE,
                null,
                request
        );

        assertEquals(request.tag(), contactPhone.getTag());
        assertEquals(request.number(), contactPhone.getNumber());
        assertEquals(request.displayOrder(), contactPhone.getDisplayOrder());
    }

    @Test
    public void givenContactPhoneId_shouldDeleteContactPhone() {
        assertDoesNotThrow(() -> service.delete(
                ComponentName.CONTACT_PHONE,
                id
        ));
    }
}
