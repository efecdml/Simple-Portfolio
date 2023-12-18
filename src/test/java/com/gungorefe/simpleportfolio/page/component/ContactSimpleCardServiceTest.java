package com.gungorefe.simpleportfolio.page.component;

import com.gungorefe.simpleportfolio.dto.Image;
import com.gungorefe.simpleportfolio.dto.page.component.ComponentDto;
import com.gungorefe.simpleportfolio.dto.page.component.ContactSimpleCardDto;
import com.gungorefe.simpleportfolio.dto.page.component.CreateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.dto.page.component.UpdateContactSimpleCardRequest;
import com.gungorefe.simpleportfolio.entity.page.component.ContactSimpleCard;
import com.gungorefe.simpleportfolio.service.ImageService;
import com.gungorefe.simpleportfolio.service.page.component.ComponentService;
import com.gungorefe.simpleportfolio.vo.ComponentName;
import com.gungorefe.simpleportfolio.vo.LocaleName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Sql(scripts = {"/contact-data.sql"})
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class ContactSimpleCardServiceTest {
    @Autowired
    private ComponentService componentService;
    @Autowired
    private ImageService imageService;
    private final String imageName = "image1.jpg";
    private final int id = 1;

    @Test
    public void givenContactSimpleCardImageNameAndId1_shouldReturnContactSimpleCardDto() {
        ComponentDto dto = componentService.getDto(
                ComponentName.CONTACT_SIMPLE_CARD,
                id
        );

        assertEquals(dto.getClass(), ContactSimpleCardDto.class);
    }

    @Test
    public void givenCreateContactSimpleCardRequestWithMultipartFile_shouldCreateContactSimpleCard() {
        CreateContactSimpleCardRequest request = new CreateContactSimpleCardRequest(
                "created title",
                "created text",
                1
        );
        Image image = imageService.get(imageName);
        MultipartFile imageFile = new MockMultipartFile(
                imageName,
                imageName,
                MediaType.IMAGE_JPEG_VALUE,
                image.bytes()
        );
        ContactSimpleCard contactSimpleCard = (ContactSimpleCard) componentService.create(
                ComponentName.CONTACT_SIMPLE_CARD,
                imageFile,
                request,
                LocaleName.ENGLISH.value
        );

        assertEquals(request.title(), contactSimpleCard.getTitle());
        assertEquals(request.text(), contactSimpleCard.getText());
        assertEquals(request.displayOrder(), contactSimpleCard.getDisplayOrder());
    }

    @Test
    public void givenUpdateContactSimpleCardRequest_shouldUpdateContactSimpleCard() {
        UpdateContactSimpleCardRequest request = new UpdateContactSimpleCardRequest(
                id,
                "updated title",
                "updated text",
                6
        );
        ContactSimpleCard contactSimpleCard = (ContactSimpleCard) componentService.update(
                ComponentName.CONTACT_SIMPLE_CARD,
                null,
                request
        );

        assertEquals(request.title(), contactSimpleCard.getTitle());
        assertEquals(request.text(), contactSimpleCard.getText());
        assertEquals(request.displayOrder(), contactSimpleCard.getDisplayOrder());
    }

    @Test
    public void givenContactSimpleCardId_shouldDeleteContactSimpleCard() {
        assertDoesNotThrow(() -> componentService.delete(
                ComponentName.CONTACT_SIMPLE_CARD,
                id
        ));
        assertThrowsExactly(
                FileNotFoundException.class,
                () -> imageService.get(this.imageName)
        );
    }
}
